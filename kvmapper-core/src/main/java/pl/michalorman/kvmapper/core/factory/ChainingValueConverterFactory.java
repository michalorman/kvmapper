package pl.michalorman.kvmapper.core.factory;

import pl.michalorman.kvmapper.core.converter.ValueConverter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * This value converter factory organizes other value converters in chain of responsibility
 * and delegates task of creating of value converters each of them.
 * <p/>
 * This component also buffers received value converters. It means that for certain method
 * the chain will be invoked only once.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class ChainingValueConverterFactory implements ValueConverterFactory {

    /** Root factory in chain. */
    private ChainedValueConverterFactory chainRoot;

    /** Buffers already resolved value converters. */
    private Map<Method, ValueConverter> converters = new HashMap<Method, ValueConverter>();

    public ChainingValueConverterFactory() {
        // create the chain
        chainRoot = new AnnotationValueConverterFactory();
        chainRoot.setSuccessor(new EnumValueConverterFactory())
                .setSuccessor(new BuiltInJavaTypeValueConverterFactory());

    }

    public ValueConverter getValueConverter(Method method) {
        if (!converters.containsKey(method)) {
            converters.put(method, chainRoot.getValueConverter(method));
        }
        return converters.get(method);
    }
}
