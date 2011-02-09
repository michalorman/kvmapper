package pl.michalorman.kvmapper.core.serializer;


import pl.michalorman.kvmapper.core.annotation.OrderProperties;
import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.introspect.ReadableProperty;
import pl.michalorman.kvmapper.core.introspect.TypeDescription;
import pl.michalorman.kvmapper.core.introspect.TypeIntrospector;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

    public void serialize(Appendable output, Iterator<?> iterator, Config config, TypeIntrospector typeIntrospector) throws IOException {
        while (iterator.hasNext()) serialize(output, iterator.next(), config, typeIntrospector, !iterator.hasNext());
    }

    private void serialize(Appendable output, Object object, Config config, TypeIntrospector typeIntrospector, boolean isLastElement) throws IOException {
        TypeDescription description = getTypeDescription(object.getClass(), typeIntrospector, config);
        Iterator<ReadableProperty> iterator = getProperties(object.getClass(), description).iterator();
        while (iterator.hasNext()) {
            ReadableProperty property = iterator.next();
            output.append(property.getName());
            output.append(config.getKeyValueSeparator());
            output.append(property.getValue(object));
            if (iterator.hasNext()) {
                output.append(config.getPairSeparator());
            }
        }
        if (!isLastElement) output.append(config.getPairSeparator());
    }

    private Collection<ReadableProperty> getProperties(Class<?> type, TypeDescription description) {
        return !type.isAnnotationPresent(OrderProperties.class) ?
                description.getReadableProperties() :
                description.getReadablePropertiesInOrder(type.getAnnotation(OrderProperties.class).value());
    }

    private TypeDescription getTypeDescription(Class<?> type, TypeIntrospector typeIntrospector, Config config) {
        if (!descriptions.containsKey(type)) {
            descriptions.put(type, typeIntrospector.introspect(type, config));
        }
        return descriptions.get(type);
    }
}
