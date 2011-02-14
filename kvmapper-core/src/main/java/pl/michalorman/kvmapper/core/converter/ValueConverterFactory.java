package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.exception.KVMapperException;

import java.lang.reflect.Method;
import java.util.Date;
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
        Class<?> type = getType(method);
        if (!defaultConverters.containsKey(type) && method.isAnnotationPresent(pl.michalorman.kvmapper.core.annotation.ValueConverter.class)) {
            ValueConverter converter = createNewValueConverter(method);
            defaultConverters.put(type, converter);
        } else if (type.isEnum()) {
            return defaultConverters.get(Enum.class);
        }
        return defaultConverters.get(type);
    }

    private ValueConverter createNewValueConverter(Method method) {
        try {
            Class<? extends ValueConverter> type = method.getAnnotation(pl.michalorman.kvmapper.core.annotation.ValueConverter.class).value();
            return type.newInstance();
        } catch (InstantiationException e) {
            throw new KVMapperException("Cannot create new instance of value converter.", e);
        } catch (IllegalAccessException e) {
            throw new KVMapperException("Cannot create new instance of value converter.", e);
        }
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
        defaultConverters.put(Short.class, new ShortValueConverter());
        defaultConverters.put(Integer.class, new IntegerValueConverter());
        defaultConverters.put(Long.class, new LongValueConverter());
        defaultConverters.put(Float.class, new FloatValueConverter());
        defaultConverters.put(Double.class, new DoubleValueConverter());
        defaultConverters.put(Boolean.class, new BooleanValueConverter());
        defaultConverters.put(Character.class, new CharacterValueConverter());
        defaultConverters.put(String.class, new StringValueConverter());
        defaultConverters.put(Date.class, new DateValueConverter());
        defaultConverters.put(Enum.class, new EnumValueConverter());
    }
}
