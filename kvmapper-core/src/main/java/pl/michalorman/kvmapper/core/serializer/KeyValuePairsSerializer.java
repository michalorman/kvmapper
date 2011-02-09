package pl.michalorman.kvmapper.core.serializer;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.introspect.TypeIntrospector;

import java.io.IOException;
import java.util.Iterator;

/**
 * Interface for components performing the serialization of object to key-value pairs.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface KeyValuePairsSerializer {
    /**
     * Serializes all objects received by the specified <tt>iterator</tt>.
     *
     * @param output           Output to which append the serialization result.
     * @param iterator         Iterator used to traverse all objects to serialize.
     * @param config           Serialization config.
     * @param typeIntrospector Component performing type introspection.
     */
    void serialize(Appendable output, Iterator<?> iterator, Config config, TypeIntrospector typeIntrospector) throws IOException;
}
