package pl.michalorman.kvmapper.core.parser;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.introspect.TypeIntrospector;

import java.io.IOException;
import java.io.InputStream;

/**
 * Interface for components performing parsing the key-value pairs.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface KeyValuePairsParser {
    /**
     * Parse specified <tt>input</tt> for key-value pairs and instantiates specified <tt>type</tt>
     * setting the properties to values according to data read from the input.
     *
     * @param input            Input to be parsed.
     * @param type             Class to be populated.
     * @param config           Current framework configuration.
     * @param typeIntrospector Component performing the type introspection.
     * @param <T>              Type of object to be instantiated.
     *
     * @return New instance of given type populated according to specified input.
     */
    <T> T parse(InputStream input, Class<T> type, Config config, TypeIntrospector typeIntrospector) throws IOException;
}
