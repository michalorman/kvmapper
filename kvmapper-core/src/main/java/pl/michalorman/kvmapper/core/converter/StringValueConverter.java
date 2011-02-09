package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link String} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class StringValueConverter extends JavaTypeValueConverter<String> {
    public StringValueConverter() {
        // strings are always objects thus default value is always null
        super(null);
    }

    public String getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return value;
    }
}
