package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Boolean} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class BooleanValueConverter extends JavaTypeValueConverter<Boolean> {
    public BooleanValueConverter(Boolean defaultValue) {
        super(defaultValue);
    }

    public BooleanValueConverter() {
        this(null);
    }

    public Boolean getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Boolean.valueOf(value);
    }
}
