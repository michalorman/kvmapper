package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;

/**
 * Interface for components instantiating the {@link pl.michalorman.kvmapper.core.converter.ValueConverter}
 * objects.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface ValueConverterFactory {

    /**
     * Creates and returns the value converter for specified method (getter or setter).
     *
     * @param method Method for which create the value converter.
     *
     * @return Value converter.
     */
    ValueConverter getValueConverter(Method method);

}
