package pl.michalorman.kvmapper.annotation;

import java.lang.annotation.*;

/**
 * Indicates the order in which properties should be serialized. If this annotation
 * is not present on class while serializing, the order is not predictable.
 * <p/>
 * Properties which are specified in this annotation will be serialized as first in
 * order they appear. All other properties will be serialized after with unpredictable
 * order.
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OrderProperties {

    /** Array of property names in order they should be serialized */
    String[] value();

}
