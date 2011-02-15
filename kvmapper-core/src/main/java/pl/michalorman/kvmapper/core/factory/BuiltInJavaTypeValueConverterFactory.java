package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.*;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static pl.michalorman.kvmapper.core.util.MethodUtils.getType;

/**
 * Creates the value value converter for supported built-in Java types. Does not takes into
 * consideration any annotation incl. {@link pl.michalorman.kvmapper.core.annotation.ValueConverter}
 * (but the value converters may use the annotations).
 *
 * @author Michal Orman
 * @version 1.0
 */
public class BuiltInJavaTypeValueConverterFactory extends ChainedValueConverterFactory {
    /** Value converters for built-in Java types. */
    private static final Map<Class, ValueConverter> CONVERTERS = new HashMap<Class, ValueConverter>();

    static {
        CONVERTERS.put(short.class, new ShortValueConverter(Short.MIN_VALUE));
        CONVERTERS.put(int.class, new IntegerValueConverter(Integer.MIN_VALUE));
        CONVERTERS.put(long.class, new LongValueConverter(Long.MIN_VALUE));
        CONVERTERS.put(float.class, new FloatValueConverter(Float.MIN_VALUE));
        CONVERTERS.put(double.class, new DoubleValueConverter(Double.MIN_VALUE));
        CONVERTERS.put(boolean.class, new BooleanValueConverter(Boolean.FALSE));
        CONVERTERS.put(char.class, new CharacterValueConverter(Character.MIN_VALUE));

        CONVERTERS.put(Short.class, new ShortValueConverter());
        CONVERTERS.put(Integer.class, new IntegerValueConverter());
        CONVERTERS.put(Long.class, new LongValueConverter());
        CONVERTERS.put(Float.class, new FloatValueConverter());
        CONVERTERS.put(Double.class, new DoubleValueConverter());
        CONVERTERS.put(Boolean.class, new BooleanValueConverter());
        CONVERTERS.put(Character.class, new CharacterValueConverter());
        CONVERTERS.put(String.class, new StringValueConverter());
        CONVERTERS.put(Date.class, new DateValueConverter());
    }

    protected ValueConverter getOrCreateValueConverter(Method method) {
        return CONVERTERS.get(getType(method));
    }
}
