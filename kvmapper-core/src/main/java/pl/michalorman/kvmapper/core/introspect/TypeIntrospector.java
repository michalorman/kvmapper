package pl.michalorman.kvmapper.core.introspect;

import pl.michalorman.kvmapper.core.config.Config;

/**
 * Interface for objects performing the type introspection.
 *
 * @author Michal Orman
 * @version 1.0
 */
public interface TypeIntrospector {
    /**
     * Performs introspection of the given <tt>type</tt> and returns the result as
     * an instance of {@link TypeDescription} class.
     *
     * @param type   Type to introspect.
     * @param config Current configuration.
     *
     * @return Result of introspection as an instance of {@link TypeDescription} class.
     */
    TypeDescription introspect(Class<?> type, Config config);
}
