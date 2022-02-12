package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.util.Util;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents the attributes of a class file.
 */
public class AttributeReader {

    private static final Map<String, Class<? extends AttributeDesc>> attributeClasses;

    static {
        attributeClasses = new HashMap<>();
        for (Class<?> clazz : Util.findClassesInPackage(AttributeReader.class.getPackageName())) {
            if (!AttributeDesc.class.isAssignableFrom(clazz) || !clazz.isAnnotationPresent(AttributeName.class)) {
                continue;
            }
            final AttributeName name = clazz.getAnnotation(AttributeName.class);
            attributeClasses.put(name.value(), clazz.asSubclass(AttributeDesc.class));
        }
    }

    /**
     * Private constructor to prevent instantiation.
     */
    private AttributeReader() {

    }

    public static void submitAttribute(final Class<? extends CustomAttribute> clazz) {
        Objects.requireNonNull(clazz);
        if (!AttributeDesc.class.isAssignableFrom(clazz) || !clazz.isAnnotationPresent(AttributeName.class)) {
            throw new IllegalArgumentException("Class does not meet requirements to be submitted");
        }
        final AttributeName name = clazz.getAnnotation(AttributeName.class);
        if (attributeClasses.containsKey(name.value())) {
            throw new IllegalArgumentException("Attribute with name already registered");
        }
        attributeClasses.put(name.value(), clazz.asSubclass(AttributeDesc.class));
    }

    /**
     * Reads the attributes of a class file from the given data input stream.
     *
     * @param in  the data input stream to read from
     * @param ref the containing class file to reference
     * @return an AttributeDesc containing the attributes of the class file
     * @throws IOException if an I/O error occurs
     */
    public static AttributeDesc read(final DataInputStream in, final ClassFile ref) throws IOException {
        final int attributeNameIndex = in.readUnsignedShort();
        final String attributeName = ref.getConstantDesc(attributeNameIndex) instanceof UTF8Constant u ? u.getValue() : null;
        if (attributeName == null) {
            throw new IllegalArgumentException("Attribute Name Index must point to a UTF 8 Constant");
        }
        final Class<? extends AttributeDesc> clazz = attributeClasses.getOrDefault(attributeName, UnknownAttribute.class);
        try {
            final Method readMethod = clazz.getDeclaredMethod("read", int.class, DataInputStream.class);
            readMethod.setAccessible(true);
            return (AttributeDesc) readMethod.invoke(null, attributeNameIndex, in);
        } catch (NoSuchMethodException ignored) {
        } catch (SecurityException ex) {
            throw new IllegalStateException("Security Exception????", ex);
        } catch (IllegalAccessException ex) {
            throw new Error("How?? I set it to accessible???");
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }
        try {
            final Method readMethod = clazz.getDeclaredMethod("read", int.class, DataInputStream.class, ClassFile.class);
            readMethod.setAccessible(true);
            return (AttributeDesc) readMethod.invoke(null, attributeNameIndex, in, ref);
        } catch (NoSuchMethodException ex) {
            throw new IllegalStateException("Attribute Class with invalid read method");
        } catch (SecurityException ex) {
            throw new IllegalStateException("Security Exception????", ex);
        } catch (IllegalAccessException ex) {
            throw new Error("How?? I set it to accessible???");
        } catch (InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
