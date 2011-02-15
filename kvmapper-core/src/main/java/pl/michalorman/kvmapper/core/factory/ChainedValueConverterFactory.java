package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;

/**
 * Base class for value converters encapsulating common methods and fields.
 *
 * @author Michal Orman
 * @version 1.0
 */
public abstract class ChainedValueConverterFactory implements ValueConverterFactory {

    /** Next value converter in chain. */
    private ChainedValueConverterFactory successor;

    public ChainedValueConverterFactory setSuccessor(ChainedValueConverterFactory successor) {
        this.successor = successor;
        return successor;
    }

    public ValueConverter getValueConverter(Method method) {
        ValueConverter converter = getOrCreateValueConverter(method);
        if (converter == null && successor != null) {
            converter = successor.getValueConverter(method);
        }
        return converter;
    }

    protected abstract ValueConverter getOrCreateValueConverter(Method method);
}
