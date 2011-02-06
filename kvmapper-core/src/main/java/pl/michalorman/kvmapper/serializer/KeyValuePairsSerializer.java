package pl.michalorman.kvmapper.serializer;

import pl.michalorman.kvmapper.config.Config;
import pl.michalorman.kvmapper.introspect.TypeIntrospector;

/**
 * Interface for components performing the serialization of object to key-value pairs.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface KeyValuePairsSerializer {
    /**
     * Serializes specified <tt>object</tt> and appends the result to the specified <tt>output</tt>.
     *
     * @param output Output to which append the serialization result.
     * @param object Object to serialize.
     * @param config Serialization config.
     * @param typeIntrospector Component performing type introspection.
     */
    void serialize(Appendable output, Object object, Config config, TypeIntrospector typeIntrospector);
}
