package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;

import java.lang.reflect.Method;

/**
 * Interface for components performing the value conversion to and from string for
 * serialization and deserialization purposes.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface ValueConverter<T> {

    /**
     * Converts specified value< to string representation for serialization. The method is
     * passed the getter method, for which the conversion is being performed. This is useful
     * for having access to annotations present on the method.
     *
     * @param value  Value to convert.
     * @param getter Getter method for which the conversion is performed.
     * @param config Current configuration.
     *
     * @return String representation of the specified <tt>value</tt>.
     */
    String toString(T value, Method getter, Config config);

    /**
     * Converts the specified value from the string representation to the actual
     * type for deserialization.
     *
     * @param value  Value to convert.
     * @param setter Setter method for which the conversion is performed.
     * @param config Current configuration.
     *
     * @return Object parsed from the specified string.
     */
    T fromString(String value, Method setter, Config config);

}
