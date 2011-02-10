package pl.michalorman.kvmapper.core.annotation;

import java.lang.annotation.*;

/**
 * Indicates the date format to be used when serializing and deserializing annotated
 * property. The acceptable format are those that accepts the {@link java.text.SimpleDateFormat} class.
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormat {

    /** Date format */
    String value();

}
