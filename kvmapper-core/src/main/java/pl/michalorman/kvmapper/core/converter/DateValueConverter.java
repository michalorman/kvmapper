package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.annotation.DateFormat;
import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.exception.ValueConversionException;

import java.lang.reflect.AnnotatedElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Value converter for {@link java.util.Date} type.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class DateValueConverter extends JavaTypeValueConverter<Date> {
    protected DateValueConverter() {
        super(null);
    }

    @Override
    public String toString(Date value, AnnotatedElement annotatedElement, Config config) {
        if (annotatedElement.isAnnotationPresent(DateFormat.class)) {
            java.text.DateFormat dateFormat = new SimpleDateFormat(annotatedElement.getAnnotation(DateFormat.class).value());
            return dateFormat.format(value);
        }
        if (config.isDateFormatConfigured()) {
            return config.getDateFormat().format(value);
        }
        return String.valueOf(value.getTime());
    }

    protected Date getFromString(String value, AnnotatedElement annotatedElement, Config config) {
        try {
            if (annotatedElement.isAnnotationPresent(DateFormat.class)) {
                java.text.DateFormat dateFormat = new SimpleDateFormat(annotatedElement.getAnnotation(DateFormat.class).value());
                return dateFormat.parse(value);
            }
            if (config.isDateFormatConfigured()) {
                return config.getDateFormat().parse(value);
            }
        } catch (ParseException e) {
            throw new ValueConversionException("Unable to parse date using configured date format.", e);
        }
        return new Date(Long.valueOf(value));
    }
}
