package pl.michalorman.kvmapper.crypto.converter;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.converter.ValueConverter;

import javax.crypto.SecretKey;
import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.crypto.utils.CipherUtils.decrypt;
import static pl.michalorman.kvmapper.crypto.utils.CipherUtils.encrypt;

/**
 * The decorator class for other value converters allowing encrypting and decrypting
 * single value (from key-value pair).
 *
 * @author Michal Orman
 * @version 1.0
 */
public class EncryptingValueConverter<T> implements ValueConverter<T> {

    /** Decorated value converter */
    private ValueConverter<T> valueConverter;

    private SecretKey key;

    /** Algorithm to use for encryption/decryption. */
    private String algorithm;

    /**
     * Class constructor.
     *
     * @param valueConverter Converter to decorate.
     * @param algorithm      Algorithm to be used for ecnryption/decryption.
     * @param key            Key to use for encryption/decryption.
     */
    public EncryptingValueConverter(ValueConverter<T> valueConverter, String algorithm, SecretKey key) {
        this.valueConverter = valueConverter;
        this.key = key;
        this.algorithm = algorithm;
    }

    public String toString(T value, Method getter, Config config) {
        String result = valueConverter.toString(value, getter, config);
        return encrypt(algorithm, key, result);
    }

    public T fromString(String value, Method setter, Config config) {
        String decrypted = decrypt(algorithm, key, value);
        return valueConverter.fromString(decrypted, setter, config);
    }
}
