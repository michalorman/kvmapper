package pl.michalorman.kvmapper.converter;

import pl.michalorman.kvmapper.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Boolean} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class BooleanValueConverter extends JavaTypeValueConverter<Boolean> {
    public Boolean getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Boolean.valueOf(value);
    }
}
