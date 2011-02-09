package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Double} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class DoubleValueConverter extends JavaTypeValueConverter<Double> {
    public DoubleValueConverter(Double defaultValue) {
        super(defaultValue);
    }

    public Double getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return Double.valueOf(value);
    }
}
