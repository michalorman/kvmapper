package pl.michalorman.kvmapper.core.annotation;

/**
 * Indicates that given property should be serialized or deserialized using specified
 * value converter.
 * <p/>
 * This annotation specifies the class implementing {@link pl.michalorman.kvmapper.core.converter.ValueConverter}
 * interface that will be instantiated and configured to convert values for annotated
 * property.
 * <p/>
 * Note that if annotation is applied to getter method the value converter will be
 * used during serialization. If same value converter should be used for deserialization
 * same annotation should be applied to corresponding setter method.
 *
 * @author Michal Orman
 * @version 1.0
 */
public @interface ValueConverter {

    /** Class of value converter to be used for serialization and deserialization. */
    Class<? extends pl.michalorman.kvmapper.core.converter.ValueConverter> value();

}
