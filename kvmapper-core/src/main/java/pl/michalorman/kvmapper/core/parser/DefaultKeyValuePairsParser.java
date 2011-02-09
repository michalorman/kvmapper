package pl.michalorman.kvmapper.core.parser;

import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.exception.KVMapperException;
import pl.michalorman.kvmapper.core.introspect.TypeDescription;
import pl.michalorman.kvmapper.core.introspect.TypeIntrospector;
import pl.michalorman.kvmapper.core.introspect.WritableProperty;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Default implementation of {@link KeyValuePairsParser} interface.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class DefaultKeyValuePairsParser implements KeyValuePairsParser {
    @SuppressWarnings({ "unchecked" })
    public <T> T parse(InputStream input, Class<T> type, Config config, TypeIntrospector typeIntrospector) throws IOException {
        TypeDescription typeDescription = typeIntrospector.introspect(type, config);
        Object instance = createInstance(type);
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter(String.valueOf(config.getPairSeparator()));
        try {
            // iterate over each pair
            while (scanner.hasNext()) {
                parseLine(scanner.next(), instance, typeDescription, config);
            }
        } finally {
            // shall parser close the input? maybe an option in config?
            scanner.close();
        }
        return (T) instance;
    }

    private <T> Object createInstance(Class<T> type) {
        try {
            return type.newInstance();
        } catch (InstantiationException e) {
            throw new KVMapperException("Cannot create instance of " + type.getCanonicalName(), e);
        } catch (IllegalAccessException e) {
            throw new KVMapperException("Cannot create instance of " + type.getCanonicalName(), e);
        }
    }

    private <T> void parseLine(String pair, Object instance, TypeDescription typeDescription, Config config) {
        Scanner scanner = new Scanner(pair);
        scanner.useDelimiter(String.valueOf(config.getKeyValueSeparator()));
        if (scanner.hasNext()) {
            String key = next(scanner);
            String value = next(scanner);
            setProperty(instance, key, value, typeDescription, config);
        }
    }

    private void setProperty(Object instance, String propertyName, String value, TypeDescription typeDescription, Config config) {
        if (typeDescription.hasWritableProperty(propertyName)) {
            WritableProperty property = typeDescription.getWritableProperty(propertyName);
            property.setValue(instance, value);
        }
    }

    private String next(Scanner scanner) {
        String token = scanner.next();
        return token != null ? token.trim() : token;
    }
}
