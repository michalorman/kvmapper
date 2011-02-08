package pl.michalorman.kvmapper.mapper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class KVMapperTest {

    private KVMapper mapper;

    @BeforeMethod
    public void setUp() {
        mapper = new KVMapper();
    }

    /*==========================================================================
        Test serialization
     */

    @DataProvider
    public Object[][] dataForTestOnPrimitives() {
        return new Object[][]{
                { Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.MAX_VALUE, Double.MAX_VALUE, true, Character.MAX_VALUE },
                { Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.MIN_VALUE, Double.MIN_VALUE, false, Character.MIN_VALUE }
        };
    }

    @Test(description = "Should serialize primitive properties of an object and write result to an appendable.", dataProvider = "dataForTestOnPrimitives")
    public void shouldWriteObjectToAppendable(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                                              boolean typeBoolean, char typeChar) throws IOException {
        Primitives target = new Primitives(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar);
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertTrue(result.contains("typeShort=" + String.valueOf(typeShort) + "\n"), "Should contain key-value pair for a typeShort property");
        assertTrue(result.contains("typeInt=" + String.valueOf(typeInt) + "\n"), "Should contain key-value pair for a typeInt property");
        assertTrue(result.contains("typeLong=" + String.valueOf(typeLong) + "\n"), "Should contain key-value pair for a typeLong property");
        assertTrue(result.contains("typeFloat=" + String.valueOf(typeFloat) + "\n"), "Should contain key-value pair for a typeFloat property");
        assertTrue(result.contains("typeDouble=" + String.valueOf(typeDouble) + "\n"), "Should contain key-value pair for a typeDouble property");
        assertTrue(result.contains("typeBoolean=" + String.valueOf(typeBoolean) + "\n"), "Should contain key-value pair for a typeBoolean property");
        assertTrue(result.contains("typeChar=" + String.valueOf(typeChar) + "\n"), "Should contain key-value pair for a typeChar property");
    }

    @DataProvider
    public Object[][] dataForTestOnTypes() {
        return new Object[][]{
                { Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.MAX_VALUE, Double.MAX_VALUE, true, Character.MAX_VALUE, "Test" },
                { Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.MIN_VALUE, Double.MIN_VALUE, false, Character.MIN_VALUE, "Test" }
        };
    }

    @Test(description = "Should serialize reference type properties of an object and write result to an appendable.", dataProvider = "dataForTestOnTypes")
    public void shouldWriteObjectToAppendable(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                                              Boolean typeBoolean, Character typeChar, String typeString) throws IOException {
        Types target = new Types(typeShort, typeInt, typeLong, typeFloat, typeDouble, typeBoolean, typeChar, typeString);
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertTrue(result.contains("typeShort=" + String.valueOf(typeShort) + "\n"), "Should contain key-value pair for a typeShort property");
        assertTrue(result.contains("typeInt=" + String.valueOf(typeInt) + "\n"), "Should contain key-value pair for a typeInt property");
        assertTrue(result.contains("typeLong=" + String.valueOf(typeLong) + "\n"), "Should contain key-value pair for a typeLong property");
        assertTrue(result.contains("typeFloat=" + String.valueOf(typeFloat) + "\n"), "Should contain key-value pair for a typeFloat property");
        assertTrue(result.contains("typeDouble=" + String.valueOf(typeDouble) + "\n"), "Should contain key-value pair for a typeDouble property");
        assertTrue(result.contains("typeBoolean=" + String.valueOf(typeBoolean) + "\n"), "Should contain key-value pair for a typeBoolean property");
        assertTrue(result.contains("typeChar=" + String.valueOf(typeChar) + "\n"), "Should contain key-value pair for a typeChar property");
        assertTrue(result.contains("typeString=" + typeString + "\n"), "Should contain key-value pair for a typeString property");
    }

    @Test(description = "Should serialize object and write null properties as serialization result.")
    public void shouldWriteObjectWithNullProperties() throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertTrue(result.contains("typeShort=null\n"), "Should contain key-value pair for a typeShort property");
        assertTrue(result.contains("typeInt=null\n"), "Should contain key-value pair for a typeInt property");
        assertTrue(result.contains("typeLong=null\n"), "Should contain key-value pair for a typeLong property");
        assertTrue(result.contains("typeFloat=null\n"), "Should contain key-value pair for a typeFloat property");
        assertTrue(result.contains("typeDouble=null\n"), "Should contain key-value pair for a typeDouble property");
        assertTrue(result.contains("typeBoolean=null\n"), "Should contain key-value pair for a typeBoolean property");
        assertTrue(result.contains("typeChar=null\n"), "Should contain key-value pair for a typeChar property");
        assertTrue(result.contains("typeString=null\n"), "Should contain key-value pair for a typeString property");
    }

    @Test(description = "Should serialize object using configured key-values and pairs separator.")
    public void shouldWriteObjectUsingConfiguredSeparators() throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.getConfig().setKeyValueSeparator(':');
        mapper.getConfig().setPairSeparator(',');
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertTrue(result.contains("typeShort:null,"), "Should contain key-value pair for a typeShort property");
        assertTrue(result.contains("typeInt:null,"), "Should contain key-value pair for a typeInt property");
        assertTrue(result.contains("typeLong:null,"), "Should contain key-value pair for a typeLong property");
        assertTrue(result.contains("typeFloat:null,"), "Should contain key-value pair for a typeFloat property");
        assertTrue(result.contains("typeDouble:null,"), "Should contain key-value pair for a typeDouble property");
        assertTrue(result.contains("typeBoolean:null,"), "Should contain key-value pair for a typeBoolean property");
        assertTrue(result.contains("typeChar:null,"), "Should contain key-value pair for a typeChar property");
        assertTrue(result.contains("typeString:null,"), "Should contain key-value pair for a typeString property");
    }

    /*==========================================================================
        Classes used in tests
     */

    public class Primitives {
        private short typeShort;
        private int typeInt;
        private long typeLong;
        private float typeFloat;
        private double typeDouble;
        private boolean typeBoolean;
        private char typeChar;

        Primitives() {
        }

        Primitives(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble, boolean typeBoolean, char typeChar) {
            this.typeShort = typeShort;
            this.typeInt = typeInt;
            this.typeLong = typeLong;
            this.typeFloat = typeFloat;
            this.typeDouble = typeDouble;
            this.typeBoolean = typeBoolean;
            this.typeChar = typeChar;
        }

        public short getTypeShort() {
            return typeShort;
        }

        public void setTypeShort(short typeShort) {
            this.typeShort = typeShort;
        }

        public int getTypeInt() {
            return typeInt;
        }

        public void setTypeInt(int typeInt) {
            this.typeInt = typeInt;
        }

        public long getTypeLong() {
            return typeLong;
        }

        public void setTypeLong(long typeLong) {
            this.typeLong = typeLong;
        }

        public float getTypeFloat() {
            return typeFloat;
        }

        public void setTypeFloat(float typeFloat) {
            this.typeFloat = typeFloat;
        }

        public double getTypeDouble() {
            return typeDouble;
        }

        public void setTypeDouble(double typeDouble) {
            this.typeDouble = typeDouble;
        }

        public boolean isTypeBoolean() {
            return typeBoolean;
        }

        public void setTypeBoolean(boolean typeBoolean) {
            this.typeBoolean = typeBoolean;
        }

        public char getTypeChar() {
            return typeChar;
        }

        public void setTypeChar(char typeChar) {
            this.typeChar = typeChar;
        }
    }

    public class Types {
        private Short typeShort;
        private Integer typeInt;
        private Long typeLong;
        private Float typeFloat;
        private Double typeDouble;
        private Boolean typeBoolean;
        private Character typeChar;
        private String typeString;

        public Types() {
        }

        public Types(Short typeShort, Integer typeInt, Long typeLong, Float typeFloat, Double typeDouble,
                     Boolean typeBoolean, Character typeChar, String typeString) {
            this.typeShort = typeShort;
            this.typeInt = typeInt;
            this.typeLong = typeLong;
            this.typeFloat = typeFloat;
            this.typeDouble = typeDouble;
            this.typeBoolean = typeBoolean;
            this.typeChar = typeChar;
            this.typeString = typeString;
        }

        public Short getTypeShort() {
            return typeShort;
        }

        public void setTypeShort(Short typeShort) {
            this.typeShort = typeShort;
        }

        public Integer getTypeInt() {
            return typeInt;
        }

        public void setTypeInt(Integer typeInt) {
            this.typeInt = typeInt;
        }

        public Long getTypeLong() {
            return typeLong;
        }

        public void setTypeLong(Long typeLong) {
            this.typeLong = typeLong;
        }

        public Float getTypeFloat() {
            return typeFloat;
        }

        public void setTypeFloat(Float typeFloat) {
            this.typeFloat = typeFloat;
        }

        public Double getTypeDouble() {
            return typeDouble;
        }

        public void setTypeDouble(Double typeDouble) {
            this.typeDouble = typeDouble;
        }

        public Boolean getTypeBoolean() {
            return typeBoolean;
        }

        public void setTypeBoolean(Boolean typeBoolean) {
            this.typeBoolean = typeBoolean;
        }

        public Character getTypeChar() {
            return typeChar;
        }

        public void setTypeChar(Character typeChar) {
            this.typeChar = typeChar;
        }

        public String getTypeString() {
            return typeString;
        }

        public void setTypeString(String typeString) {
            this.typeString = typeString;
        }
    }

    public class Basic {
        private String property;

        public Basic(String property) {
            this.property = property;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }
    }
}