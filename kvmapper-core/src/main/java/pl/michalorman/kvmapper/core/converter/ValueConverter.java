package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.AnnotatedElement;

/**
 * Interface for components performing the value conversion to and from string for
 * serialization and deserialization purposes.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface ValueConverter<T> {

    /**
     * Converts specified <tt>value</tt> to string representation for serialization. The
     * method will be passed <tt>annotatedElement</tt> which represents the property for
     * which the conversion is performed, and is useful if conversion process may be altered
     * by other annotations.
     *
     * @param value            Value to convert.
     * @param annotatedElement Annotated element corresponding to property for which conversion
     *                         is performed.
     * @param config           Current configuration.
     *
     * @return String representation of the specified <tt>value</tt>.
     */
    String toString(T value, AnnotatedElement annotatedElement, Config config);

    /**
     * Converts the specified <tt>value</tt> from the string representation to the actual
     * type for deserialization.
     *
     * @param value            Value to convert.
     * @param annotatedElement Annotated element corresponding to property for which conversion
     *                         is performed.
     * @param config           Current configuration.
     *
     * @return Object parsed from the specified string.
     */
    T fromString(String value, AnnotatedElement annotatedElement, Config config);

}
