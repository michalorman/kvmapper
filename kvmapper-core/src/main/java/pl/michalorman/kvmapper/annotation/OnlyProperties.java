package pl.michalorman.kvmapper.annotation;

import java.lang.annotation.*;

/**
 * Indicates properties that only should be included in serialization and deserialization.
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OnlyProperties {

    /** Array of property names to include. */
    String[] value();

}
