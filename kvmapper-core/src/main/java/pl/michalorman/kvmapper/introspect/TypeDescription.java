package pl.michalorman.kvmapper.introspect;

import pl.michalorman.kvmapper.config.Config;
import pl.michalorman.kvmapper.converter.ValueConverter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static pl.michalorman.kvmapper.util.MethodUtils.*;

/**
 * Wraps set of information introspected from the certain type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class TypeDescription {
    /** Type this description concerns. */
    private Class<?> type;

    /** Maps type's properties. */
    private Map<String, Property> properties = new HashMap<String, Property>();

    private Config config;

    public TypeDescription(Class<?> type, Config config) {
        this.type = type;
        this.config = config;
    }

    /**
     * Returns the set of type properties.
     * @return set of properties.
     */
    public Set<Property> getProperties() {
        return new HashSet<Property>(properties.values());
    }

    public void addProperty(String propertyName, Method method, ValueConverter valueConverter) {
        Property property = getProperty(propertyName);
        property.setValueConverter(valueConverter);
        property.setType(getType(method));
        if (hasSetterSignature(method)) {
            property.setSetterMethod(method);
        } else if (hasGetterSignature(method)) {
            property.setGetterMethod(method);
        }
    }

    private Property getProperty(String propertyName) {
        if (!properties.containsKey(propertyName)) {
            properties.put(propertyName, new Property(propertyName, config));
        }
        return properties.get(propertyName);
    }
}
