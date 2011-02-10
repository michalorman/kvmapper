package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.Method;

/**
 * Value converter for {@link Short} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class ShortValueConverter extends JavaTypeValueConverter<Short> {
    public ShortValueConverter(Short defaultValue) {
        super(defaultValue);
    }

    public ShortValueConverter() {
        this(null);
    }

    public Short getFromString(String value, Method setter, Config config) {
        return Short.valueOf(value);
    }
}
