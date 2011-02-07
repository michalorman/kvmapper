package pl.michalorman.kvmapper.converter;

import pl.michalorman.kvmapper.config.Config;

import java.lang.reflect.AnnotatedElement;

import static pl.michalorman.kvmapper.util.StringUtils.isBlank;

/**
 * Abstract class for Java built-in type value converters.
 *
 * @author Michal Orman
 * @version 1.0
 */
public abstract class JavaTypeValueConverter<T> implements ValueConverter<T> {
    public String toString(T value, AnnotatedElement annotatedElement, Config config) {
        return String.valueOf(value);
    }

    public T fromString(String value, AnnotatedElement annotatedElement, Config config) {
        return isBlank(value) ? null : getFromString(value, annotatedElement, config);
    }

    protected abstract T getFromString(String value, AnnotatedElement annotatedElement, Config config);
}
