package pl.michalorman.kvmapper.core.exception;

/**
 * Thrown if any exception occurs while performing value conversion.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class ValueConversionException extends RuntimeException {
    public ValueConversionException() {
    }

    public ValueConversionException(String s) {
        super(s);
    }

    public ValueConversionException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValueConversionException(Throwable throwable) {
        super(throwable);
    }
}
