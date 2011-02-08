package pl.michalorman.kvmapper.serializer;


import pl.michalorman.kvmapper.config.Config;
import pl.michalorman.kvmapper.introspect.Property;
import pl.michalorman.kvmapper.introspect.TypeDescription;
import pl.michalorman.kvmapper.introspect.TypeIntrospector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of {@link KeyValuePairsSerializer} interface.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class DefaultKeyValuePairsSerializer implements KeyValuePairsSerializer {
    /** Buffers the descriptions of type being introspected. */
    private Map<Class<?>, TypeDescription> descriptions = new HashMap<Class<?>, TypeDescription>();

    public void serialize(Appendable output, Object object, Config config, TypeIntrospector typeIntrospector) throws IOException {
        TypeDescription description = getTypeDescription(object.getClass(), typeIntrospector, config);
        for (Property property : description.getProperties()) {
            output.append(property.getName());
            output.append(config.getKeyValueSeparator());
            output.append(property.getValue(object));
            output.append(config.getPairSeparator());
        }
    }

    private TypeDescription getTypeDescription(Class<?> type, TypeIntrospector typeIntrospector, Config config) {
        if (!descriptions.containsKey(type)) {
            descriptions.put(type, typeIntrospector.introspect(type, config));
        }
        return descriptions.get(type);
    }
}
