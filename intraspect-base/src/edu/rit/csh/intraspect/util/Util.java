package edu.rit.csh.intraspect.util;

import java.lang.reflect.Array;
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

    public static <T> T[] addElement(T[] array, T element) {
        @SuppressWarnings("unchecked")
        T[] nArr = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length + 1);
        System.arraycopy(array, 0, nArr, 0, array.length);
        nArr[array.length] = element;
        return nArr;
    }

    public static <T> T[] addElement(T[] array, T element, int index) {
        if (index > array.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        T[] nArr = addElement(array, element);
        for (int i = nArr.length - 1; i > index; i--) {
            nArr[i] = nArr[i - 1];
        }
        nArr[index] = element;
        return nArr;
    }

    public static <T> T[] removeElement(T[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        @SuppressWarnings("unchecked")
        T[] nArr = (T[]) Array.newInstance(array.getClass().getComponentType(), array.length - 1);
        for (int i = 0; i < nArr.length; i++) {
            nArr[i] = array[i >= index ? i + 1 : i];
        }
        return nArr;
    }

    public static int[] addElement(int[] array, int element) {
        @SuppressWarnings("unchecked")
        int[] nArr = new int[array.length + 1];
        System.arraycopy(array, 0, nArr, 0, array.length);
        nArr[array.length] = element;
        return nArr;
    }

    public static int[] addElement(int[] array, int element, int index) {
        if (index > array.length || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        int[] nArr = addElement(array, element);
        for (int i = nArr.length - 1; i > index; i--) {
            nArr[i] = nArr[i - 1];
        }
        nArr[index] = element;
        return nArr;
    }

    public static int[] removeElement(int[] array, int index) {
        if (index < 0 || index >= array.length) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        @SuppressWarnings("unchecked")
        int[] nArr = new int[array.length - 1];
        for (int i = 0; i < nArr.length; i++) {
            nArr[i] = array[i >= index ? i + 1 : i];
        }
        return nArr;
    }
}
