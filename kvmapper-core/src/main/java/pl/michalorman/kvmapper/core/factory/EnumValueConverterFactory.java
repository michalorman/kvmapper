package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.EnumValueConverter;
import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;

import static pl.michalorman.kvmapper.core.util.MethodUtils.getType;

/**
 * Factory that creates the value converter if property is an enumeration.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class EnumValueConverterFactory extends ChainedValueConverterFactory {
    protected ValueConverter getOrCreateValueConverter(Method method) {
        ValueConverter converter = null;
        Class<?> type = getType(method);
        if (type.isEnum()) {
            converter = new EnumValueConverter();
        }
        return converter;
    }
}
