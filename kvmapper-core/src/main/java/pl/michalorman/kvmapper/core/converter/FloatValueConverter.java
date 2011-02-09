package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Float} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class FloatValueConverter extends JavaTypeValueConverter<Float> {
    public FloatValueConverter(Float defaultValue) {
        super(defaultValue);
    }

    public Float getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Float.valueOf(value);
    }
}
