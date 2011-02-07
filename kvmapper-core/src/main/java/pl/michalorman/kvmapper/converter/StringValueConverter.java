package pl.michalorman.kvmapper.converter;

import pl.michalorman.kvmapper.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link String} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class StringValueConverter extends JavaTypeValueConverter<String> {
    public String getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return value;
    }
}
