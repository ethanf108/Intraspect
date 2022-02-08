package edu.rit.csh.intraspect.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class for common methods.
 */
public class Util {

    /**
     * Private constructor to prevent instantiation.
     */
    private Util() {
    }

    private static Class<?> getClass(final String className, final String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new Error("How");
        }
    }

    public static Set<Class<?>> findClassesInPackage(final String packageName) {
        return new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"))))).lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }
}
