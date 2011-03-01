package pl.michalorman.kvmapper.core.fixture;

import pl.michalorman.kvmapper.core.annotation.ValueConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValueConverter(CustomValueConverter.class)
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CSV {
}
