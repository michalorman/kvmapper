package pl.michalorman.kvmapper.crypto.exception;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class KVMapperCryptoException extends RuntimeException {
    public KVMapperCryptoException() {
    }

    public KVMapperCryptoException(String message) {
        super(message);
    }

    public KVMapperCryptoException(String message, Throwable cause) {
        super(message, cause);
    }

    public KVMapperCryptoException(Throwable cause) {
        super(cause);
    }
}
