package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Value converter for {@link Character} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class CharacterValueConverter extends JavaTypeValueConverter<Character> {
    public Character getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        return value.charAt(0);
    }
}
