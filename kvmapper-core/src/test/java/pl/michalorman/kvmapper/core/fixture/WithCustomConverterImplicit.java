package pl.michalorman.kvmapper.core.fixture;

public class WithCustomConverterImplicit {
    private String[] property;

    public WithCustomConverterImplicit() {
    }

    public WithCustomConverterImplicit(String[] property) {
        this.property = property;
    }

    @CSV
    public String[] getProperty() {
        return property;
    }

    @CSV
    public void setProperty(String[] property) {
        this.property = property;
    }
}
