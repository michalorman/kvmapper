package pl.michalorman.kvmapper.introspect;

import pl.michalorman.kvmapper.config.Config;
import pl.michalorman.kvmapper.exception.KVMapperException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Represents property eligible for being overwritten. Such properties are used
 * during serialization and values are set using bound setter method.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class WritableProperty extends Property {

    /** Method to set property value. */
    private Method setterMethod;

    public WritableProperty(String name, Config config) {
        super(name, config);
    }

    public void setValue(Object target, String arg) {
        try {
            setterMethod.invoke(target, valueConverter.fromString(arg, setterMethod, config));
        } catch (IllegalAccessException e) {
            throw new KVMapperException("Failed to invoke setter method.", e);
        } catch (InvocationTargetException e) {
            throw new KVMapperException("Failed to invoke setter method.", e);
        }
    }

    public void setSetterMethod(Method setterMethod) {
        this.setterMethod = setterMethod;
    }
}
