package pl.michalorman.kvmapper.core.serializer;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.introspect.TypeIntrospector;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/**
 * Interface for components performing the serialization of object to key-value pairs.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface KeyValuePairsSerializer {

    /**
     * Serializes all each object retrieved using provided iterator and writes output
     * to provided stream.
     *
     * @param output           Output where write the serialization result.
     * @param iterator         Iterator to use to iterate over each element to serialize.
     * @param config           Current framework configuration.
     * @param typeIntrospector Component performing type introspection.
     *
     * @throws IOException thrown if any errors occurs while writing to stream.
     */
    void serialize(OutputStream output, Iterator<?> iterator, Config config, TypeIntrospector typeIntrospector) throws IOException;
}
