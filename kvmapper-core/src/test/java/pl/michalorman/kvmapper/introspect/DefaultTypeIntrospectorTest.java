package pl.michalorman.kvmapper.introspect;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.michalorman.kvmapper.config.Config;

import java.util.Collection;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class DefaultTypeIntrospectorTest {

    private Config config;

    private TypeIntrospector typeIntrospector;

    @BeforeMethod
    public void setUp() {
        config = new Config();
        typeIntrospector = new DefaultTypeIntrospector();
    }

    public class WithProperties {
        private String stringProperty;
        private Boolean booleanProperty;

        public String getStringProperty() {
            return stringProperty;
        }

        public void setStringProperty(String stringProperty) {
            this.stringProperty = stringProperty;
        }

        public Boolean isBooleanProperty() {
            return booleanProperty;
        }

        public void setBooleanProperty(Boolean booleanProperty) {
            this.booleanProperty = booleanProperty;
        }
    }

    @Test(description = "Should correctly identify properties during introspection.")
    public void shouldIntrospectProperties() {
        TypeDescription result = typeIntrospector.introspect(WithProperties.class, config);
        Collection<Property> properties = result.getProperties();
        assertEquals(properties.size(), 2, "Should identify exactly 2 properties");
        assertTrue(containsProperty(properties, "stringProperty", String.class), "Should contain 'stringProperty' of type String");
        assertTrue(containsProperty(properties, "booleanProperty", Boolean.class), "Should contain 'booleanProperty' of type Boolean");
    }

    public class WithPrimitiveProperties {
        private double doubleProperty;
        private boolean booleanProperty;

        public double getDoubleProperty() {
            return doubleProperty;
        }

        public void setDoubleProperty(double doubleProperty) {
            this.doubleProperty = doubleProperty;
        }

        public boolean isBooleanProperty() {
            return booleanProperty;
        }

        public void setBooleanProperty(boolean booleanProperty) {
            this.booleanProperty = booleanProperty;
        }
    }

    @Test(description = "Should correctly identify primitive properties during introspection.")
    public void shouldIntrospectPrimitiveProperties() {
        TypeDescription result = typeIntrospector.introspect(WithPrimitiveProperties.class, config);
        Collection<Property> properties = result.getProperties();
        assertEquals(properties.size(), 2, "Should identify exactly 2 properties");
        assertTrue(containsProperty(properties, "doubleProperty", double.class), "Should contain 'doubleProperty' of type Double");
        assertTrue(containsProperty(properties, "booleanProperty", boolean.class), "Should contain 'booleanProperty' of type Boolean");
    }

    public class WithoutProperties {
        private String notProperty;

        public void getNotProperty() {
        }

        public String getNotProperty(Boolean flag) {
            return notProperty;
        }

        public void setNotProperty(String notProperty, Boolean flag) {
            this.notProperty = notProperty;
        }

        public Boolean setNotProperty(String notProperty) {
            this.notProperty = notProperty;
            return false;
        }
    }

    @Test(description = "Should omit other fields and methods which are not properties during introspection.")
    public void shouldOmitOtherFieldsAndMethods() {
        TypeDescription result = typeIntrospector.introspect(WithoutProperties.class, config);
        assertTrue(result.getProperties().isEmpty(), "Description should not have any properties.");
    }

    private boolean containsProperty(Collection<Property> properties, String propertyName, Class<?> type) {
        for (Property property : properties) {
            if (property.getName().equals(propertyName) && property.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
