package pl.michalorman.kvmapper.converter;

import pl.michalorman.kvmapper.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Short} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class ShortValueConverter extends JavaTypeValueConverter<Short> {
    public Short getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Short.valueOf(value);
    }
}
