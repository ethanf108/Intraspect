package data;

import data.attribute.UnknownAttribute;
import data.constant.UTF8Constant;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import util.Util;
import static util.Util.readShort;

public class AttributeReader {

    private static final Map<String, Class<? extends AttributeDesc>> attributeClasses;

    static {
        attributeClasses = new HashMap<>();
        for (Class<?> clazz : Util.findClassesInPackage("data.attribute")) {
            if (!AttributeDesc.class.isAssignableFrom(clazz) || !clazz.isAnnotationPresent(AttributeName.class)) {
                continue;
            }
            final AttributeName name = clazz.getAnnotation(AttributeName.class);
            attributeClasses.put(name.value(), clazz.asSubclass(AttributeDesc.class));
        }
    }

    private AttributeReader() {

    }

    public static void submitAttribute(Class<? extends AttributeDesc> clazz) {
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

    public static AttributeDesc read(DataInputStream in, ClassFile ref) throws IOException {
        final short attributeNameIndex = readShort(in);
        final String attributeName = ref.getConstandDesc(attributeNameIndex) instanceof UTF8Constant u ? u.getValue() : null;
        if (attributeName == null) {
            throw new IllegalArgumentException("Attribute Name Index must point to a UTF 8 Constant");
        }
        final Class<? extends AttributeDesc> clazz = attributeClasses.getOrDefault(attributeName, UnknownAttribute.class);
        try {
            final Method readMethod = clazz.getDeclaredMethod("read", short.class, DataInputStream.class);
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
            final Method readMethod = clazz.getDeclaredMethod("read", short.class, DataInputStream.class, ClassFile.class);
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
