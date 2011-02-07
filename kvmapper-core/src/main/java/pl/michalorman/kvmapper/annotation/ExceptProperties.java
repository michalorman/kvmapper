package pl.michalorman.kvmapper.annotation;

import java.lang.annotation.*;

/**
 * Specifies the names of properties that should be excluded from the serialization and
 * deserialization.
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptProperties {

    /** Array of property names to exclude. */
    String[] value();

}
