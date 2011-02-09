package pl.michalorman.kvmapper.core.converter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static pl.michalorman.kvmapper.core.util.MethodUtils.getType;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class ValueConverterFactory {
    private Map<Class, ValueConverter> defaultConverters = new HashMap<Class, ValueConverter>();

    public ValueConverterFactory() {
        initPrimitiveConverters();
        initTypeConverters();
    }

    public ValueConverter getValueConverter(Method method) {
        // TODO: add instantiating value converter from the annotation
        return defaultConverters.get(getType(method));
    }

    private void initPrimitiveConverters() {
        defaultConverters.put(short.class, new ShortValueConverter(Short.MIN_VALUE));
        defaultConverters.put(int.class, new IntegerValueConverter(Integer.MIN_VALUE));
        defaultConverters.put(long.class, new LongValueConverter(Long.MIN_VALUE));
        defaultConverters.put(float.class, new FloatValueConverter(Float.MIN_VALUE));
        defaultConverters.put(double.class, new DoubleValueConverter(Double.MIN_VALUE));
        defaultConverters.put(boolean.class, new BooleanValueConverter(Boolean.FALSE));
        defaultConverters.put(char.class, new CharacterValueConverter(Character.MIN_VALUE));
    }

    private void initTypeConverters() {
        defaultConverters.put(Short.class, new ShortValueConverter(null));
        defaultConverters.put(Integer.class, new IntegerValueConverter(null));
        defaultConverters.put(Long.class, new LongValueConverter(null));
        defaultConverters.put(Float.class, new FloatValueConverter(null));
        defaultConverters.put(Double.class, new DoubleValueConverter(null));
        defaultConverters.put(Boolean.class, new BooleanValueConverter(null));
        defaultConverters.put(Character.class, new CharacterValueConverter(null));
        defaultConverters.put(String.class, new StringValueConverter());
    }
}
