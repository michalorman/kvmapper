package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.Method;

/**
 * Value converter for {@link Character} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class CharacterValueConverter extends JavaTypeValueConverter<Character> {
    public CharacterValueConverter(Character defaultValue) {
        super(defaultValue);
    }

    public CharacterValueConverter() {
        this(null);
    }

    public Character getFromString(String value, Method setter, Config config) {
        return value.charAt(0);
    }
}
