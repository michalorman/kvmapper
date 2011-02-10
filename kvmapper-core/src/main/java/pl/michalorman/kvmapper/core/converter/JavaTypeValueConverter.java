package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.core.util.StringUtils.isBlank;

/**
 * Abstract class for Java built-in type value converters.
 *
 * @author Michal Orman
 * @version 1.0
 */
public abstract class JavaTypeValueConverter<T> implements ValueConverter<T> {
    private final T defaultValue;

    protected JavaTypeValueConverter(T defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String toString(T value, Method getter, Config config) {
        return String.valueOf(value);
    }

    public T fromString(String value, Method setter, Config config) {
        return isBlank(value) ? defaultValue : getFromString(value, setter, config);
    }

    protected abstract T getFromString(String value, Method setter, Config config);
}
