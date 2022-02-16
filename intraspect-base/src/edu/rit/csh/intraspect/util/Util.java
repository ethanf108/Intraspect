package edu.rit.csh.intraspect.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for common methods.
 */
public class Util {

    /**
     * Private constructor to prevent instantiation.
     */
    private Util() {
    }

    public static List<Field> getAllFields(Class<?> clazz) {
        List<Field> ret = new ArrayList<>();
        for (Field f : clazz.getDeclaredFields()) {
            f.setAccessible(true);
            ret.add(f);
        }
        if (clazz.getSuperclass() != Object.class) {
            ret.addAll(getAllFields(clazz.getSuperclass()));
        }
        return ret;
    }
}
