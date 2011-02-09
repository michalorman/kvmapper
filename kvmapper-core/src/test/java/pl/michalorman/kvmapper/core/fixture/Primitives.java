package pl.michalorman.kvmapper.core.fixture;

import pl.michalorman.kvmapper.core.annotation.OrderProperties;

/**
 * @author Michal Orman
 * @version 1.0
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

    public Primitives() {
    }

    public Primitives(short typeShort, int typeInt, long typeLong, float typeFloat, double typeDouble, boolean typeBoolean, char typeChar) {
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
