package pl.michalorman.kvmapper.core.annotation;

import java.lang.annotation.*;

/**
 * This annotation serves two purposes. When annotating getter or setter method explicitly indicates
 * tha class of {@link pl.michalorman.kvmapper.core.converter.ValueConverter} that should be used to
 * convert value while serializing or deserializing. This annotation serves also as meta-annotation for
 * other annotations that indicates value converters, which can make annotation more explicit, hide the
 * exact {@link pl.michalorman.kvmapper.core.converter.ValueConverter} type, and provides a bit more
 * configuration capabilities.
 * <p/>
 * To explicitly indicate the value converter class to use annotate the getter or setter (note when
 * annotating getter the value converter will be used only when serializing, if required also during
 * deserialization corresponding setter should also be annotated). For example:
 * <pre>
 *     public class Pojo {
 *         &#64;ValueConverter(CustomConverter.class)
 *         public Object getProperty { return property; }
 *     }
 * </pre>
 * If you do not want to specify exact type at property level you can create new annotation and
 * annotate using this meta-annotation:
 * <pre>
 *     &#64;ValueConverter(CustomConverter.class)
 *     public @interface CustomConverter {}
 *
 *     public class Pojo {
 *         &#64;CustomConverter
 *         public Object getProperty { return property; }
 *     }
 * </pre>
 *
 * @author Michal Orman
 * @version 1.0
 */
@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValueConverter {

    /** Class of value converter to be used for serialization and deserialization. */
    Class<? extends pl.michalorman.kvmapper.core.converter.ValueConverter> value();

}
