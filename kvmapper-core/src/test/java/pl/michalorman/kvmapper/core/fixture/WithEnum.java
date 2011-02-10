package pl.michalorman.kvmapper.core.fixture;

import java.lang.annotation.ElementType;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class WithEnum {
    private ElementType elementType;

    public WithEnum() {
    }

    public WithEnum(ElementType elementType) {
        this.elementType = elementType;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }
}
