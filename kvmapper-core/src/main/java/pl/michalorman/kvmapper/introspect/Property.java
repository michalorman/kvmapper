package pl.michalorman.kvmapper.introspect;

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

    public Property(String name) {
        this.name = name;
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

    public String getConvertedValue(Object object) {
        return null;
    }

    public void setType(Class<?> type) {
        this.type = type;
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
}
