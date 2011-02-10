package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.Method;

/**
 * Value converter for {@link Long} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class LongValueConverter extends JavaTypeValueConverter<Long> {
    public LongValueConverter(Long defaultValue) {
        super(defaultValue);
    }

    public LongValueConverter() {
        this(null);
    }

    public Long getFromString(String value, Method setter, Config config) {
        return Long.valueOf(value);
    }
}
