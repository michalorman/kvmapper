package pl.michalorman.kvmapper.converter;

import pl.michalorman.kvmapper.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Integer} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class IntegerValueConverter extends JavaTypeValueConverter<Integer> {
    public Integer getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Integer.valueOf(value);
    }
}
