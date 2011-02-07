package pl.michalorman.kvmapper.introspect;

import pl.michalorman.kvmapper.annotation.ExceptProperties;
import pl.michalorman.kvmapper.annotation.IgnoreProperty;
import pl.michalorman.kvmapper.annotation.OnlyProperties;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.util.ArrayUtils.includes;
import static pl.michalorman.kvmapper.util.MethodUtils.hasGetterSignature;
import static pl.michalorman.kvmapper.util.MethodUtils.hasSetterSignature;
import static pl.michalorman.kvmapper.util.StringUtils.uncapitalized;

/**
 * Default implementation of {@link TypeIntrospector} interface.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class DefaultTypeIntrospector implements TypeIntrospector {
    public TypeDescription introspect(Class<?> type) {
        TypeDescription description = new TypeDescription(type);
        Method[] methods = type.getMethods();
        for (Method method : methods) {
            if (isProperty(method)) {
                String propertyName = getPropertyName(method);
                if (isNotIgnored(method, propertyName, type)) {
                    description.addProperty(propertyName, method);
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
