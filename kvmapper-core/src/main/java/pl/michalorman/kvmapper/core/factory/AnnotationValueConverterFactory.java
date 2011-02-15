package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.ValueConverter;
import pl.michalorman.kvmapper.core.exception.KVMapperException;

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
        ValueConverter converter = null;
        if (isAnnotated(method)) {
            converter = createNewValueConverter(method);
        }
        return converter;
    }

    private boolean isAnnotated(Method method) {
        return method.isAnnotationPresent(pl.michalorman.kvmapper.core.annotation.ValueConverter.class);
    }

    private ValueConverter createNewValueConverter(Method method) {
        try {
            Class<? extends ValueConverter> type = method.getAnnotation(pl.michalorman.kvmapper.core.annotation.ValueConverter.class).value();
            return type.newInstance();
        } catch (InstantiationException e) {
            throw new KVMapperException("Cannot create new instance of value converter.", e);
        } catch (IllegalAccessException e) {
            throw new KVMapperException("Cannot create new instance of value converter.", e);
        }
    }
}
