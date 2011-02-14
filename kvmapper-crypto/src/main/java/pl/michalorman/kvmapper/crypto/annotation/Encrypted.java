package pl.michalorman.kvmapper.crypto.annotation;

/**
 * Indicates that value assigned or read from annotated property is or should be encrypted.
 *
 * During serialization the annotated property will be encrypted using the
 * {@link pl.michalorman.kvmapper.crypto.converter.EncryptingValueConverter} object.
 *
 * @author Michal Orman
 * @version 1.0
 */
public @interface Encrypted {
}
