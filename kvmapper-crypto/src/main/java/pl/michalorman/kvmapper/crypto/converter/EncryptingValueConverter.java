package pl.michalorman.kvmapper.crypto.converter;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;

/**
 * The decorator class for other value converters allowing encrypting and decrypting
 * single value (from key-value pair).
 *
 * @author Michal Orman
 * @version 1.0
 */
public class EncryptingValueConverter<T> implements ValueConverter<T> {
    public String toString(T value, Method getter, Config config) {
        return null;  // TODO: implement body
    }

    public T fromString(String value, Method setter, Config config) {
        return null;  // TODO: implement body
    }
}
