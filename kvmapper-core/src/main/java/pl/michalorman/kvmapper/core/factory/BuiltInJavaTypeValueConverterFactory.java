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
    private Map<Class, ValueConverter> converters = new HashMap<Class, ValueConverter>();

    protected BuiltInJavaTypeValueConverterFactory() {
        initPrimitiveConverters();
        initTypeConverters();
    }

    protected ValueConverter getOrCreateValueConverter(Method method) {
        return converters.get(getType(method));
    }

    private void initPrimitiveConverters() {
        converters.put(short.class, new ShortValueConverter(Short.MIN_VALUE));
        converters.put(int.class, new IntegerValueConverter(Integer.MIN_VALUE));
        converters.put(long.class, new LongValueConverter(Long.MIN_VALUE));
        converters.put(float.class, new FloatValueConverter(Float.MIN_VALUE));
        converters.put(double.class, new DoubleValueConverter(Double.MIN_VALUE));
        converters.put(boolean.class, new BooleanValueConverter(Boolean.FALSE));
        converters.put(char.class, new CharacterValueConverter(Character.MIN_VALUE));
    }

    private void initTypeConverters() {
        converters.put(Short.class, new ShortValueConverter());
        converters.put(Integer.class, new IntegerValueConverter());
        converters.put(Long.class, new LongValueConverter());
        converters.put(Float.class, new FloatValueConverter());
        converters.put(Double.class, new DoubleValueConverter());
        converters.put(Boolean.class, new BooleanValueConverter());
        converters.put(Character.class, new CharacterValueConverter());
        converters.put(String.class, new StringValueConverter());
        converters.put(Date.class, new DateValueConverter());
    }
}
