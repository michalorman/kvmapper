package pl.michalorman.kvmapper.core.converter;

import pl.michalorman.kvmapper.core.annotation.DateFormat;
import pl.michalorman.kvmapper.core.config.Config;
import pl.michalorman.kvmapper.core.exception.ValueConversionException;

import java.lang.reflect.Method;
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
    public String toString(Date value, Method getter, Config config) {
        if (getter.isAnnotationPresent(DateFormat.class)) {
            java.text.DateFormat dateFormat = new SimpleDateFormat(getter.getAnnotation(DateFormat.class).value());
            return dateFormat.format(value);
        }
        if (config.isDateFormatConfigured()) {
            return config.getDateFormat().format(value);
        }
        return String.valueOf(value.getTime());
    }

    protected Date getFromString(String value, Method setter, Config config) {
        try {
            if (setter.isAnnotationPresent(DateFormat.class)) {
                java.text.DateFormat dateFormat = new SimpleDateFormat(setter.getAnnotation(DateFormat.class).value());
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
