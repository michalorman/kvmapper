package pl.michalorman.kvmapper.core.config;

import java.text.DateFormat;

import static pl.michalorman.kvmapper.core.util.ArrayUtils.includes;

/**
 * The configuration to be used for serialization and deserialization.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class Config {

    /** Character that separates keys and values */
    private char keyValueSeparator = '=';

    /** Character that separates key-value pairs */
    private char pairSeparator = '\n';

    /**
     * Date format to be used when serializing dates. By default no format is used,
     * which means that the dates are serialized as milliseconds (long value).
     */
    private DateFormat dateFormat = null;

    /**
     * Name of properties, that should be excluded from serialization and
     * deserialization by default.
     */
    private String[] disallowedProperties = { "class" };

    public char getKeyValueSeparator() {
        return keyValueSeparator;
    }

    public void setKeyValueSeparator(char keyValueSeparator) {
        this.keyValueSeparator = keyValueSeparator;
    }

    public char getPairSeparator() {
        return pairSeparator;
    }

    public void setPairSeparator(char pairSeparator) {
        this.pairSeparator = pairSeparator;
    }

    public String[] getDisallowedProperties() {
        return disallowedProperties;
    }

    public void setDisallowedProperties(String[] disallowedProperties) {
        this.disallowedProperties = disallowedProperties;
    }

    public boolean isPropertyAllowed(String propertyName) {
        return !includes(disallowedProperties, propertyName);
    }

    public DateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public boolean isDateFormatConfigured() {
        return dateFormat != null;
    }
}
