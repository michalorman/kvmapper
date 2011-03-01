package pl.michalorman.kvmapper.core.fixture;

import pl.michalorman.kvmapper.core.annotation.ValueConverter;

public class WithCustomConverterExplicit {
    private String[] property;

    public WithCustomConverterExplicit() {
    }

    public WithCustomConverterExplicit(String[] property) {
        this.property = property;
    }

    @ValueConverter(CustomValueConverter.class)
    public String[] getProperty() {
        return property;
    }

    @ValueConverter(CustomValueConverter.class)
    public void setProperty(String[] property) {
        this.property = property;
    }
}
