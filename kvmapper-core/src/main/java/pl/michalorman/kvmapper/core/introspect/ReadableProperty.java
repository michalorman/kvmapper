package pl.michalorman.kvmapper.core.introspect;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.exception.KVMapperException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represents the property eligible for being read. Such properties are used
 * during serialization and values are read using bound getter method.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class ReadableProperty extends Property {

    /** Method to get property value. */
    private Method getterMethod;

    public ReadableProperty(String name, Config config) {
        super(name, config);
    }

    @SuppressWarnings({ "unchecked" })
    public String getValue(Object target) {
        try {
            return valueConverter.toString(getterMethod.invoke(target), getterMethod, config);
        } catch (IllegalAccessException e) {
            throw new KVMapperException("Failed to invoke getter method.", e);
        } catch (InvocationTargetException e) {
            throw new KVMapperException("Failed to invoke getter method.", e);
        }
    }

    public void setGetterMethod(Method getterMethod) {
        this.getterMethod = getterMethod;
    }
}
