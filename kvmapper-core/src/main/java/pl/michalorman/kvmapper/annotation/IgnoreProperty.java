package pl.michalorman.kvmapper.annotation;

import java.lang.annotation.*;

/**
 * Indicates that annotated property should be ignored for serialization or
 * deserialization.
 * <p>
 * To ignore property for serialization annotate appropriate getter method. To
 * ignore property for deserialization annotate appropriate setter method.
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreProperty {
}
