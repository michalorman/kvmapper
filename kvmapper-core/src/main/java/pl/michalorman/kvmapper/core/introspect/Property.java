package pl.michalorman.kvmapper.core.introspect;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.converter.ValueConverter;

/**
 * Abstract class for type properties.
 *
 * @author Michal Orman
 * @version 1.0
 */
public abstract class Property {
    /** Property's name. */
    private String name;

    /** Property's type. */
    private Class<?> type;

    protected ValueConverter valueConverter;

    /** Current framework configuration */
    protected Config config;

    public Property(String name, Config config) {
        this.name = name;
        this.config = config;
    }

    public String getName() {
        return name;
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
