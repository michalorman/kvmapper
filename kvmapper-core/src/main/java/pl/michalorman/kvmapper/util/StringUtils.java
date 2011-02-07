package pl.michalorman.kvmapper.util;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class StringUtils {

    /**
     * Returns the copy of the provided string with capital character in lower case.
     *
     * @param s String to return uncapitalized.
     * @return Uncapitalized string.
     */
    public static String uncapitalized(String s) {
        return Character.toLowerCase(s.charAt(0)) + s.substring(1);
    }

}
