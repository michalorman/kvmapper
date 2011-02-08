package pl.michalorman.kvmapper.core.introspect;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;
import java.util.*;

import static pl.michalorman.kvmapper.core.util.MethodUtils.*;

/**
 * Wraps set of information introspected from the certain type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class TypeDescription {
    /** Type this description concerns. */
    private Class<?> type;

    /** Properties that are eligible for being read (while serializing). */
    private Map<String, ReadableProperty> readableProperties = new HashMap<String, ReadableProperty>();

    /** Properties that are eligible for being overwritten (while deserializing). */
    private Map<String, WritableProperty> writableProperties = new HashMap<String, WritableProperty>();

    private Config config;

    public TypeDescription(Class<?> type, Config config) {
        this.type = type;
        this.config = config;
    }

    /**
     * Returns the collection of readable properties.
     *
     * @return readable properties.
     */
    public Collection<ReadableProperty> getReadableProperties() {
        return readableProperties.values();
    }

    /**
     * Returns the collection of writable properties.
     *
     * @return writable properties.
     */
    public Collection<WritableProperty> getWritableProperties() {
        return writableProperties.values();
    }

    /**
     * Returns readable properties in the order specified by the <tt>order</tt> parameters.
     * <p/>
     * Properties with names specified in <tt>order</tt> parameter are added to the beginning
     * of the collection in specified order, while remaining properties are added to the end
     * of the collection in unspecified order.
     *
     * @param order Property names in order they should be returned.
     *
     * @return Colelction of ordered properties.
     */
    public Collection<ReadableProperty> getReadablePropertiesInOrder(String[] order) {
        return getPropertiesInOrder(order, readableProperties);
    }

    private <T extends Property> Collection<T> getPropertiesInOrder(String[] order, Map<String, T> mappedProperties) {
        Map<String, T> properties = new HashMap<String, T>(mappedProperties); // copy mapped properties
        List<T> orderedProperties = new ArrayList<T>(properties.size());
        for (String propertyName : order) {
            if (properties.containsKey(propertyName)) {
                orderedProperties.add(properties.get(propertyName));
                properties.remove(propertyName);
            }
        }
        orderedProperties.addAll(properties.values());
        return orderedProperties;
    }

    public void addProperty(String propertyName, Method method, ValueConverter valueConverter) {
        Property property = null;
        if (hasSetterSignature(method)) {
            WritableProperty writableProperty = getOrCreateWritableProperty(propertyName);
            writableProperty.setSetterMethod(method);
            property = writableProperty;
        } else if (hasGetterSignature(method)) {
            ReadableProperty readableProperty = getOrCreateReadableProperty(propertyName);
            readableProperty.setGetterMethod(method);
            property = readableProperty;
        }
        // apply common properties
        if (property != null) {
            property.setType(getType(method));
            property.setValueConverter(valueConverter);
        }
    }

    private ReadableProperty getOrCreateReadableProperty(String propertyName) {
        if (!readableProperties.containsKey(propertyName)) {
            readableProperties.put(propertyName, new ReadableProperty(propertyName, config));
        }
        return readableProperties.get(propertyName);
    }

    private WritableProperty getOrCreateWritableProperty(String propertyName) {
        if (!writableProperties.containsKey(propertyName)) {
            writableProperties.put(propertyName, new WritableProperty(propertyName, config));
        }
        return writableProperties.get(propertyName);
    }
}
