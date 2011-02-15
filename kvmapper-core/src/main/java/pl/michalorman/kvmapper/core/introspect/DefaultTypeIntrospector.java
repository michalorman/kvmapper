package pl.michalorman.kvmapper.core.introspect;

import pl.michalorman.kvmapper.core.annotation.ExceptProperties;
import pl.michalorman.kvmapper.core.annotation.IgnoreProperty;
import pl.michalorman.kvmapper.core.annotation.OnlyProperties;
import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.factory.BuiltInJavaTypeValueConverterFactory;
import pl.michalorman.kvmapper.core.factory.ValueConverterFactory;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.core.util.ArrayUtils.includes;
import static pl.michalorman.kvmapper.core.util.MethodUtils.hasGetterSignature;
import static pl.michalorman.kvmapper.core.util.MethodUtils.hasSetterSignature;
import static pl.michalorman.kvmapper.core.util.StringUtils.uncapitalized;

/**
 * Default implementation of {@link TypeIntrospector} interface.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class DefaultTypeIntrospector implements TypeIntrospector {

    private ValueConverterFactory valueConverterFactory = new BuiltInJavaTypeValueConverterFactory();

    public TypeDescription introspect(Class<?> type, Config config) {
        TypeDescription description = new TypeDescription(type, config);
        Method[] methods = type.getMethods();
        for (Method method : methods) {
            if (isProperty(method)) {
                String propertyName = getPropertyName(method);
                if (isNotIgnored(method, propertyName, type) && config.isPropertyAllowed(propertyName)) {
                    description.addProperty(propertyName, method, valueConverterFactory.getValueConverter(method));
                }
            }
        }
        return description;
    }

    private boolean isNotIgnored(AnnotatedElement element, String propertyName, Class<?> type) {
        // is ignored by annotation
        if (element.isAnnotationPresent(IgnoreProperty.class)) return false;
        // is excluded by ExceptProperties annotation
        if (isPropertyExcluded(propertyName, type)) return false;
        // is included by OnlyProperties annotation (if present)
        return isPropertyIncluded(propertyName, type);
    }

    private boolean isPropertyIncluded(String propertyName, Class<?> type) {
        return !type.isAnnotationPresent(OnlyProperties.class) ||
                includes(type.getAnnotation(OnlyProperties.class).value(), propertyName);
    }

    private boolean isPropertyExcluded(String propertyName, Class<?> type) {
        return type.isAnnotationPresent(ExceptProperties.class) &&
                includes(type.getAnnotation(ExceptProperties.class).value(), propertyName);
    }

    private boolean isProperty(Method method) {
        return hasSetterSignature(method) || hasGetterSignature(method);
    }

    private String getPropertyName(Method method) {
        String name = method.getName();
        return name.startsWith("is") ? uncapitalized(name.substring(2)) : uncapitalized(name.substring(3));
    }
}
