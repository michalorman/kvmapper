package pl.michalorman.kvmapper.converter;

import pl.michalorman.kvmapper.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Long} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class LongValueConverter extends JavaTypeValueConverter<Long> {
    public Long getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Long.valueOf(value);
    }
}
