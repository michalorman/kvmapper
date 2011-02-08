package pl.michalorman.kvmapper.mapper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.michalorman.kvmapper.annotation.OrderProperties;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

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
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar));
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
        assertEquals(result, "typeShort=" + String.valueOf(typeShort) +
                "\ntypeInt=" + String.valueOf(typeInt) +
                "\ntypeLong=" + String.valueOf(typeLong) +
                "\ntypeFloat=" + String.valueOf(typeFloat) +
                "\ntypeDouble=" + String.valueOf(typeDouble) +
                "\ntypeBoolean=" + String.valueOf(typeBoolean) +
                "\ntypeChar=" + String.valueOf(typeChar) +
                "\ntypeString=" + typeString);
    }

    @Test(description = "Should serialize object and write null properties as serialization result.")
    public void shouldWriteObjectWithNullProperties() throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, "typeShort=null\ntypeInt=null\ntypeLong=null\ntypeFloat=null\ntypeDouble=null\ntypeBoolean=null\ntypeChar=null\ntypeString=null");
    }

    @Test(description = "Should serialize object using configured key-values and pairs separator.")
    public void shouldWriteObjectUsingConfiguredSeparators() throws IOException {
        Types target = new Types();
        StringBuilder builder = new StringBuilder();
        mapper.getConfig().setKeyValueSeparator(':');
        mapper.getConfig().setPairSeparator(',');
        mapper.writeObject(builder, target);
        String result = builder.toString();
        assertEquals(result, "typeShort:null,typeInt:null,typeLong:null,typeFloat:null,typeDouble:null,typeBoolean:null,typeChar:null,typeString:null");
    }

    /*==========================================================================
        Classes used in tests
     */

    @OrderProperties({ "typeShort", "typeInt", "typeLong", "typeFloat", "typeDouble", "typeBoolean", "typeChar" })
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

    @OrderProperties({ "typeShort", "typeInt", "typeLong", "typeFloat", "typeDouble", "typeBoolean", "typeChar", "typeString" })
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