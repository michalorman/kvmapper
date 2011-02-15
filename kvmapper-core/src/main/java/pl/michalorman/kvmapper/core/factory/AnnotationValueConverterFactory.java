package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.ValueConverter;
import pl.michalorman.kvmapper.core.exception.KVMapperException;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

/**
 * Value converter factory that instantiates the value converter basing on the annotations
 * present on the given method.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class AnnotationValueConverterFactory extends ChainedValueConverterFactory {
    protected ValueConverter getOrCreateValueConverter(Method method) {
        try {
            Class<? extends ValueConverter> type = getValueConverterType(method);
            return type != null ? type.newInstance() : null;
        } catch (InstantiationException e) {
            throw new KVMapperException("Cannot create new instance of value converter.", e);
        } catch (IllegalAccessException e) {
            throw new KVMapperException("Cannot create new instance of value converter.", e);
        }
    }

    private boolean isValueConverterAnnotationPreset(AnnotatedElement element) {
        return element.isAnnotationPresent(pl.michalorman.kvmapper.core.annotation.ValueConverter.class);
    }

    private Class<? extends ValueConverter> getValueConverterType(AnnotatedElement element) {
        return element.getAnnotation(pl.michalorman.kvmapper.core.annotation.ValueConverter.class).value();
    }

    private Class<? extends ValueConverter> getValueConverterType(Method method) {
        Class<? extends ValueConverter> type = null;
        if (isValueConverterAnnotationPreset(method)) {
            type = getValueConverterType(method);
        }
        for (Annotation annotation : method.getAnnotations()) {
            if (isValueConverterAnnotationPreset(annotation.annotationType())) {
                type = getValueConverterType(annotation.annotationType());
            }
        }
        return type;
    }
}
