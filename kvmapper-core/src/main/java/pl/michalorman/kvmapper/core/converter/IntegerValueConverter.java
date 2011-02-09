package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

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

    public Integer getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Integer.valueOf(value);
    }
}
