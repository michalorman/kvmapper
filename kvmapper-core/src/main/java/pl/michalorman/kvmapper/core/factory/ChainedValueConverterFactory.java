package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;

/**
 * Base class for value converters encapsulating common methods and fields.
 * <p/>
 * The value converter factories are organized in chain of responsibility. Starting
 * from the chain's root if given factory is unable to instantiate value converter
 * the task is delegated to its successor.
 * <p/>
 * The value converters may be also used separately outside of the chain.
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
