package pl.michalorman.kvmapper.introspect;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalorman.kvmapper.annotation.ExceptProperties;
import pl.michalorman.kvmapper.annotation.IgnoreProperty;
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

        Collection<ReadableProperty> readableProperties = result.getReadableProperties();
        assertEquals(readableProperties.size(), 2, "Should identify exactly 2 readable properties");
        assertTrue(containsProperty(readableProperties, "stringProperty", String.class), "Should contain readable 'stringProperty' property of type String");
        assertTrue(containsProperty(readableProperties, "booleanProperty", Boolean.class), "Should contain readable 'booleanProperty' property of type Boolean");

        Collection<WritableProperty> writableProperties = result.getWritableProperties();
        assertEquals(writableProperties.size(), 2, "Should identify exactly 2 writable properties");
        assertTrue(containsProperty(writableProperties, "stringProperty", String.class), "Should contain writable 'stringProperty' property of type String");
        assertTrue(containsProperty(writableProperties, "booleanProperty", Boolean.class), "Should contain writable 'booleanProperty' property of type Boolean");
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

        Collection<ReadableProperty> readableProperties = result.getReadableProperties();
        assertEquals(readableProperties.size(), 2, "Should identify exactly 2 readable properties");
        assertTrue(containsProperty(readableProperties, "doubleProperty", double.class), "Should contain readable 'doubleProperty' property of type Double");
        assertTrue(containsProperty(readableProperties, "booleanProperty", boolean.class), "Should contain readable 'booleanProperty' property of type Boolean");

        Collection<WritableProperty> writableProperties = result.getWritableProperties();
        assertEquals(writableProperties.size(), 2, "Should identify exactly 2 writable properties");
        assertTrue(containsProperty(writableProperties, "doubleProperty", double.class), "Should contain writable 'doubleProperty' property of type Double");
        assertTrue(containsProperty(writableProperties, "booleanProperty", boolean.class), "Should contain writable 'booleanProperty' property of type Boolean");
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
        assertTrue(result.getReadableProperties().isEmpty(), "Description should not have any readable properties.");
        assertTrue(result.getWritableProperties().isEmpty(), "Description should not have any writable properties.");
    }

    public class WithoutReadableProperty {
        private String property;

        public void setProperty(String property) {
            this.property = property;
        }
    }

    public class IgnoringReadableProperty {
        private String property;

        @IgnoreProperty
        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }

    @DataProvider
    public Object[][] dataFormOmittingReadableProperty() {
        return new Object[][] {
                { WithoutReadableProperty.class }, { IgnoringReadableProperty.class }
        };
    }

    @Test(description = "Should omit the readable property if not declared or annotated as ignored.", dataProvider = "dataFormOmittingReadableProperty")
    public void shouldOmitMissingOrIgnoredReadableProperty(Class<?> type) {
        TypeDescription result = typeIntrospector.introspect(type, config);

        Collection<WritableProperty> writableProperties = result.getWritableProperties();
        assertEquals(writableProperties.size(), 1, "Should identify exactly 1 writable properties");
        assertTrue(containsProperty(writableProperties, "property", String.class), "Should contain writable property of type String");

        assertTrue(result.getReadableProperties().isEmpty(), "Description should not have any readable properties.");
    }

    public class WithoutWritableProperty {
        private String property;

        public String getProperty() {
            return property;
        }
    }

    public class IgnoringWritableProperty {
        private String property;

        public String getProperty() {
            return property;
        }

        @IgnoreProperty
        public void setProperty(String property) {
            this.property = property;
        }
    }

    @DataProvider
    public Object[][] dataFormOmittingWritableProperty() {
        return new Object[][] {
                { WithoutWritableProperty.class }, { IgnoringWritableProperty.class }
        };
    }

    @Test(description = "Should omit the writable property if not declared or annotated as ignored.", dataProvider = "dataFormOmittingWritableProperty")
    public void shouldOmitMissingWritableProperty(Class<?> type) {
        TypeDescription result = typeIntrospector.introspect(type, config);

        Collection<ReadableProperty> readableProperties = result.getReadableProperties();
        assertEquals(readableProperties.size(), 1, "Should identify exactly 1 readable properties");
        assertTrue(containsProperty(readableProperties, "property", String.class), "Should contain readable property of type Double");

        assertTrue(result.getWritableProperties().isEmpty(), "Description should not have any writable properties.");
    }

    private boolean containsProperty(Collection<? extends Property> properties, String propertyName, Class<?> type) {
        for (Property property : properties) {
            if (property.getName().equals(propertyName) && property.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

}
