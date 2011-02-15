package pl.michalorman.kvmapper.crypto.utils;

import pl.michalorman.kvmapper.crypto.exception.KVMapperCryptoException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Encapsulates ridiculous {@link javax.crypto.Cipher} API.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class CipherUtils {

    /**
     * Encrypts the specified input using provided algorithm and key.
     *
     * @param algorithm Algorithm to use to encrypt the input.
     * @param key       Key to use for encryption.
     * @param input     Input to encrypt.
     *
     * @return Encrypted result.
     */
    public static byte[] encrypt(String algorithm, SecretKey key, byte[] input) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getEncoded(), algorithm));
            return cipher.doFinal(input);
        } catch (NoSuchAlgorithmException e) {
            throw new KVMapperCryptoException("Cannot create new cipher instance.", e);
        } catch (NoSuchPaddingException e) {
            throw new KVMapperCryptoException("Cannot create new cipher instance.", e);
        } catch (InvalidKeyException e) {
            throw new KVMapperCryptoException("Cannot initialize cipher instance.", e);
        } catch (BadPaddingException e) {
            throw new KVMapperCryptoException("Cannot encrypt input.", e);
        } catch (IllegalBlockSizeException e) {
            throw new KVMapperCryptoException("Cannot encrypt input.", e);
        }
    }

    /**
     * Encrypts the specified input using provided algorithm and key.
     *
     * @param algorithm Algorithm to use to encrypt the input.
     * @param key       Key to use for encryption.
     * @param input     Input to encrypt.
     *
     * @return Encrypted result.
     */
    public static String encrypt(String algorithm, SecretKey key, String input) {
        return String.valueOf(encrypt(algorithm, key, input.getBytes()));
    }

    /**
     * Decrypts provided encrypted input using given algorithm and key.
     *
     * @param algorithm      Algorithm to use for decryption.
     * @param key            Key to use for decryption.
     * @param encryptedInput Input to decrypt.
     *
     * @return Decrypted input.
     */
    public static byte[] decrypt(String algorithm, SecretKey key, byte[] encryptedInput) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getEncoded(), algorithm));
            return cipher.doFinal(encryptedInput);
        } catch (NoSuchAlgorithmException e) {
            throw new KVMapperCryptoException("Cannot create new cipher instance.", e);
        } catch (NoSuchPaddingException e) {
            throw new KVMapperCryptoException("Cannot create new cipher instance.", e);
        } catch (InvalidKeyException e) {
            throw new KVMapperCryptoException("Cannot initialize cipher instance.", e);
        } catch (BadPaddingException e) {
            throw new KVMapperCryptoException("Cannot decrypt input.", e);
        } catch (IllegalBlockSizeException e) {
            throw new KVMapperCryptoException("Cannot decrypt input.", e);
        }
    }

    /**
     * Decrypts provided encrypted input using given algorithm and key.
     *
     * @param algorithm      Algorithm to use for decryption.
     * @param key            Key to use for decryption.
     * @param encryptedInput Input to decrypt.
     *
     * @return Decrypted input.
     */
    public static String decrypt(String algorithm, SecretKey key, String encryptedInput) {
        return String.valueOf(decrypt(algorithm, key, encryptedInput.getBytes()));
    }

}
