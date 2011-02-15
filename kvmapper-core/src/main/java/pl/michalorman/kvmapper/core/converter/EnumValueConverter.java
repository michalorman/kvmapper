package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.exception.ValueConversionException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.core.util.MethodUtils.getType;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class EnumValueConverter extends JavaTypeValueConverter<Object> {
    public EnumValueConverter() {
        super(null);
    }

    protected Object getFromString(String value, Method setter, Config config) {
        try {
            Class<?> type = getType(setter);
            Method valueOf = type.getDeclaredMethod("valueOf", String.class);
            return valueOf.invoke(type, value);
        } catch (NoSuchMethodException e) {
            throw new ValueConversionException("Unable to get 'valueOf' method of type that supposed to be an enumeration.", e);
        } catch (InvocationTargetException e) {
            throw new ValueConversionException("Unable to invoke 'valueOf' method of type that supposed to be an enumeration.", e);
        } catch (IllegalAccessException e) {
            throw new ValueConversionException("Unable to invoke 'valueOf' method of type that supposed to be an enumeration.", e);
        }
    }
}
