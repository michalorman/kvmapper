package pl.michalorman.kvmapper.crypto.annotation;

import pl.michalorman.kvmapper.core.annotation.ValueConverter;
import pl.michalorman.kvmapper.crypto.converter.EncryptingValueConverter;

import java.lang.annotation.*;

/**
 * Indicates that value assigned or read from annotated property is or should be encrypted.
 * <p/>
 * During serialization the annotated property will be encrypted using the
 * {@link pl.michalorman.kvmapper.crypto.converter.EncryptingValueConverter} object.
 * <p/>
 * By default the AES algorithm us used for decryption/encryption. It may be changed using
 * the ``algorithm`` property of this annotation.
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ValueConverter(EncryptingValueConverter.class)
public @interface Encrypted {

    /** Specifies the algorithm to be used while encrypting/decrypting. */
    String algorithm() default "AES";

}
