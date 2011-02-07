package pl.michalorman.kvmapper.introspect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static pl.michalorman.kvmapper.util.MethodUtils.hasGetterSignature;
import static pl.michalorman.kvmapper.util.MethodUtils.hasSetterSignature;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class TypeDescription {
    /** Type this description concerns. */
    private Class<?> type;

    /** Maps type's properties. */
    private Map<String, Property> properties = new HashMap<String, Property>();

    public TypeDescription(Class<?> type) {
        this.type = type;
    }

    /**
     * Returns the set of type properties.
     * @return set of properties.
     */
    public Set<Property> getProperties() {
        return new HashSet<Property>(properties.values());
    }

    public void addProperty(String propertyName, Method method) {
        Property property = getProperty(propertyName);
        if (hasSetterSignature(method)) {
            property.setSetterMethod(method);
            property.setType(method.getParameterTypes()[0]);
        } else if (hasGetterSignature(method)) {
            property.setGetterMethod(method);
            property.setType(method.getReturnType());
        }
    }

    private Property getProperty(String propertyName) {
        if (!properties.containsKey(propertyName)) {
            properties.put(propertyName, new Property(propertyName));
        }
        return properties.get(propertyName);
    }
}
