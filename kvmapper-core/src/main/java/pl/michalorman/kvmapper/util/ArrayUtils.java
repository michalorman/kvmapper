package pl.michalorman.kvmapper.util;

import static java.util.Arrays.asList;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class ArrayUtils {

    /**
     * Checks if given <tt>array</tt> includes specified <tt>object</tt>.
     *
     * @param array Array to inclusion check.
     * @param object Object to check if included.
     * @return <tt>true</tt> if object is included, otherwise return <tt>false</tt>.
     */
    public static boolean includes(Object[] array, Object object) {
        return asList(array).contains(object);
    }

}
