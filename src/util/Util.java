package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Util {

    public static short readShort(InputStream in) throws IOException {
        return (short) ((in.read() & 0b11111111) << 8 | (in.read() & 0b11111111));
    }

    public static int readInt(InputStream in) throws IOException {
        return ByteBuffer.wrap(in.readNBytes(4)).getInt();
    }

    public static long readLong(InputStream in) throws IOException {
        return ByteBuffer.wrap(in.readNBytes(8)).getLong();
    }

    public static void writeShort(OutputStream out, short s) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.putShort(s);
        out.write(bb.array());
    }

    public static void writeInt(OutputStream out, int i) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(4);
        bb.putInt(i);
        out.write(bb.array());
    }

    public static void writeLong(OutputStream out, long l) throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(8);
        bb.putLong(l);
        out.write(bb.array());
    }

    private static Class<?> getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new Error("How");
        }
    }

    public static Set<Class<?>> findClassesInPackage(String packageName) {
        final InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));

        Objects.requireNonNull(stream);

        return new BufferedReader(new InputStreamReader(stream)).lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }
}
