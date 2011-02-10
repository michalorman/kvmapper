package pl.michalorman.kvmapper.core.exception;

/**
 * Thrown if any exceptions occurs while parsing input.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class ParserException extends RuntimeException {
    public ParserException() {
    }

    public ParserException(String s) {
        super(s);
    }

    public ParserException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ParserException(Throwable throwable) {
        super(throwable);
    }
}
