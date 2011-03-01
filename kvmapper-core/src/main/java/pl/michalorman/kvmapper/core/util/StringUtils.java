package pl.michalorman.kvmapper.core.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Set of utility methods related to {@link String} class.
 *
 * @author Michal Orman
 * @version 1.0
 */
public class StringUtils {

    /**
     * Returns the copy of the provided string with capital character in lower case.
     *
     * @param s String to return uncapitalized.
     *
     * @return Uncapitalized string.
     */
    public static String uncapitalized(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

    /**
     * Indicates if provided string has length or not. String that contains only non
     * printable characters is considered as string that has length.
     *
     * @param s String to check.
     *
     * @return <tt>true</tt> if has length, otherwise <tt>false</tt>.
     */
    public static boolean hasLength(String s) {
        return s != null && s.length() > 0;
    }

    /**
     * Indicates if provided string is blank. Non blank strings are strings that has
     * length and contains at least one printable character.
     *
     * @param s String to check.
     *
     * @return <tt>true</tt> if string is blank, otherwise <tt>false</tt>.
     */
    public static boolean isBlank(String s) {
        return !hasLength(s) || "null".equals(s) || "".equals(s.trim());
    }

    /**
     * Returns the string created by joining each string given in provided array. Each
     * string is joined and separated using the provided separator.
     *
     * @param strings   Array of strings to join.
     * @param separator Joined strings separator.
     *
     * @return Joined strings.
     */
    public static String join(String[] strings, String separator) {
        return join(Arrays.asList(strings), separator);
    }

    /**
     * Returns the string created by joining each string given in provided collection. Each
     * string is joined and separated using the provided separator.
     *
     * @param strings   Collection of strings to join.
     * @param separator Joined strings separator.
     *
     * @return Joined strings.
     */
    private static String join(Collection<String> strings, String separator) {
        StringBuilder builder = new StringBuilder();
        Iterator<String> iter = strings.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (iter.hasNext()) builder.append(separator);
        }
        return builder.toString();
    }
}
