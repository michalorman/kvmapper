package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.Method;

/**
 * Value converter for {@link Integer} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class IntegerValueConverter extends JavaTypeValueConverter<Integer> {
    public IntegerValueConverter(Integer defaultValue) {
        super(defaultValue);
    }

    public IntegerValueConverter() {
        this(null);
    }

    public Integer getFromString(String value, Method setter, Config config) {
        return Integer.valueOf(value);
    }
}
