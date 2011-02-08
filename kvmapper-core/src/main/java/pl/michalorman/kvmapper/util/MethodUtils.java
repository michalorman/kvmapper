package pl.michalorman.kvmapper.util;

import java.lang.reflect.Method;

/**
 * @author Michal Orman
 * @version 1.0
 */
public class MethodUtils {
    public static boolean hasGetterSignature(Method method) {
        return (method.getName().startsWith("get") || method.getName().startsWith("is")) &&
                method.getParameterTypes().length == 0 &&
                !(method.getReturnType().equals(Void.class) &&
                        method.getReturnType().equals(void.class));
    }

    public static boolean hasSetterSignature(Method method) {
        return method.getName().startsWith("set") &&
                method.getParameterTypes().length == 1 &&
                (method.getReturnType().equals(void.class) ||
                        method.getReturnType().equals(Void.class));
    }

    public static Class<?> getType(Method method) {
        Class<?> type = null;
        if (hasSetterSignature(method)) {
            type = method.getParameterTypes()[0];
        } else if (hasGetterSignature(method)) {
            type = method.getReturnType();
        }
        return type;
    }
}
