package pl.michalorman.kvmapper.exception;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class KVMapperException extends RuntimeException {
    public KVMapperException() {
    }

    public KVMapperException(String s) {
        super(s);
    }

    public KVMapperException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public KVMapperException(Throwable throwable) {
        super(throwable);
    }
}
