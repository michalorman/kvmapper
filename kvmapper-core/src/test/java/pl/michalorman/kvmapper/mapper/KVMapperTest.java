package pl.michalorman.kvmapper.mapper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
    public Object[][] typeValues() {
        return new Object[][] {
                { Short.MAX_VALUE, Integer.MAX_VALUE, Long.MAX_VALUE, Float.MAX_VALUE, Double.MAX_VALUE, true, Character.MAX_VALUE },
                { Short.MIN_VALUE, Integer.MIN_VALUE, Long.MIN_VALUE, Float.MIN_VALUE, Double.MIN_VALUE, false, Character.MIN_VALUE }
        };
    }

    @Test(description = "Should serialize primitive properties of an object and write to appendable.", dataProvider = "typeValues")
    public void shouldWriteObjectToAppendable(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble,
                                          boolean typeBoolean, char typeChar) {
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
}

class Primitives {
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