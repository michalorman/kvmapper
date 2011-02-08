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
        defaultConverters.put(short.class, new ShortValueConverter());
        defaultConverters.put(int.class, new IntegerValueConverter());
        defaultConverters.put(long.class, new LongValueConverter());
        defaultConverters.put(float.class, new FloatValueConverter());
        defaultConverters.put(double.class, new DoubleValueConverter());
        defaultConverters.put(boolean.class, new BooleanValueConverter());
        defaultConverters.put(char.class, new CharacterValueConverter());
    }

    private void initTypeConverters() {
        defaultConverters.put(Short.class, new ShortValueConverter());
        defaultConverters.put(Integer.class, new IntegerValueConverter());
        defaultConverters.put(Long.class, new LongValueConverter());
        defaultConverters.put(Float.class, new FloatValueConverter());
        defaultConverters.put(Double.class, new DoubleValueConverter());
        defaultConverters.put(Boolean.class, new BooleanValueConverter());
        defaultConverters.put(Character.class, new CharacterValueConverter());
        defaultConverters.put(String.class, new StringValueConverter());
    }
}
