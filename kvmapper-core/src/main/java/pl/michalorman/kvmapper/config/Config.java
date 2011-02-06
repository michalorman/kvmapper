package pl.michalorman.kvmapper.config;

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

    public void setKeyValueSeparator(char keyValueSeparator) {
        this.keyValueSeparator = keyValueSeparator;
    }

    public void setPairSeparator(char pairSeparator) {
        this.pairSeparator = pairSeparator;
    }
}
