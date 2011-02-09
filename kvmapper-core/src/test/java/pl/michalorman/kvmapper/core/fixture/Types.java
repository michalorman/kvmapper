package pl.michalorman.kvmapper.core.fixture;

import pl.michalorman.kvmapper.core.annotation.OrderProperties;

/**
 * @author Michal Orman
 * @version 1.0
 */
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
