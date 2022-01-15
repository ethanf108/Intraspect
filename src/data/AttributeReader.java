package data;

import data.attribute.UnknownAttribute;
import data.constant.UTF8Constant;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
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

    public static AttributeDesc read(InputStream in, ClassFile ref) throws IOException {
        final short attributeNameIndex = readShort(in);
        final String attributeName = ref.getConstandDesc(attributeNameIndex) instanceof UTF8Constant u ? u.getValue() : null;
        if (attributeName == null) {
            throw new IllegalArgumentException("Attribute Name Index must point to a UTF 8 Constant");
        }
        final Class<? extends AttributeDesc> clazz = attributeClasses.getOrDefault(attributeName, UnknownAttribute.class);
        try {
            final Method readMethod = clazz.getDeclaredMethod("read", new Class<?>[]{short.class, InputStream.class});
            readMethod.setAccessible(true);
            final AttributeDesc ret = (AttributeDesc) readMethod.invoke(null, attributeNameIndex, in);
            return ret;
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
