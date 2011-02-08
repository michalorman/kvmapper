package pl.michalorman.kvmapper.introspect;

import pl.michalorman.kvmapper.config.Config;
import pl.michalorman.kvmapper.converter.ValueConverter;
import pl.michalorman.kvmapper.exception.KVMapperException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wraps the type property data.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class Property {
    /** Property's name. */
    private String name;

    /** Property's type. */
    private Class<?> type;

    /** Method to set property value. */
    private Method setterMethod;
    
    /** Method to get property value. */
    private Method getterMethod;

    private ValueConverter valueConverter;

    /** Current framework configuration */
    private Config config;

    public Property(String name, Config config) {
        this.name = name;
        this.config = config;
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

    public String getName() {
        return name;
    }

    public Method getSetterMethod() {
        return setterMethod;
    }

    public void setSetterMethod(Method setterMethod) {
        this.setterMethod = setterMethod;
    }

    public Method getGetterMethod() {
        return getterMethod;
    }

    public void setGetterMethod(Method getterMethod) {
        this.getterMethod = getterMethod;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Property property = (Property) o;

        return !(name != null ? !name.equals(property.name) : property.name != null);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public void setValueConverter(ValueConverter valueConverter) {
        this.valueConverter = valueConverter;
    }
}
