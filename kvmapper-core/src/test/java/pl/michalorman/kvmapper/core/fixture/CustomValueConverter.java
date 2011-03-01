package pl.michalorman.kvmapper.core.fixture;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.core.util.StringUtils.join;

public class CustomValueConverter implements ValueConverter<String[]> {
    public String toString(String[] value, Method getter, Config config) {
        return join(value, ",");
    }

    public String[] fromString(String value, Method setter, Config config) {
        return value.split(",");
    }
}
