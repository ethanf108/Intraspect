package data;

import data.constant.*;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.util.Arrays;
import static util.Util.readShort;
import static util.Util.writeShort;

public class ClassFile {

    private static final byte[] MAGIC = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};

    private short minorVersion;
    private MajorVersion majorVersion;
    private ConstantDesc[] constantPool;
    private short accessFlags;
    private short thisClass;
    private short superClass;
    private short[] interfaces;
    private FieldDesc[] fields;
    private MethodDesc[] methods;
    private AttributeDesc[] attributes;

    private ClassFile() {

    }

    public ConstantDesc getConstandDesc(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Constant Pool entires are 1-indexed");
        }
        return this.constantPool[index];
    }

    public MajorVersion getMajorVersion() {
        return this.majorVersion;
    }

    public ConstantDesc[] getConstants() {
        ConstantDesc[] ret = new ConstantDesc[this.constantPool.length - 1];
        System.arraycopy(this.constantPool, 1, ret, 0, this.constantPool.length - 1);
        return ret;
    }

    public AttributeDesc[] getAttributes() {
        return this.attributes;
    }

    public FieldDesc[] getFields() {
        return fields;
    }

    public MethodDesc[] getMethods() {
        return methods;
    }

    public void write(OutputStream out) throws IOException {
        out.write(MAGIC);
        writeShort(out, this.minorVersion);
        writeShort(out, this.majorVersion.getMajorVersion());

        writeShort(out, (short) this.constantPool.length);
        for (int i = 1; i < this.constantPool.length; i++) {
            this.constantPool[i].write(out);
        }

        writeShort(out, this.accessFlags);
        writeShort(out, this.thisClass);
        writeShort(out, this.superClass);

        writeShort(out, (short) this.interfaces.length);
        for (short s : this.interfaces) {
            writeShort(out, s);
        }

        writeShort(out, (short) this.fields.length);
        for (FieldDesc field : this.fields) {
            field.write(out);
        }

        writeShort(out, (short) this.methods.length);
        for (MethodDesc method : this.methods) {
            method.write(out);
        }

        writeShort(out, (short) this.attributes.length);
        for (AttributeDesc attr : this.attributes) {
            attr.write(out);
        }
        out.flush();
    }

    private static ConstantDesc readConstant(DataInputStream in) throws IOException {
        return switch (in.read()) {
            case 1 ->
                UTF8Constant.read(in);
            case 3 ->
                IntegerConstant.read(in);
            case 4 ->
                FloatConstant.read(in);
            case 5 ->
                LongConstant.read(in);
            case 6 ->
                DoubleConstant.read(in);
            case 7 ->
                new ClassConstant(readShort(in));
            case 8 ->
                new StringConstant(readShort(in));
            case 9 ->
                new FieldRefConstant(readShort(in), readShort(in));
            case 10 ->
                new MethodRefConstant(readShort(in), readShort(in));
            case 11 ->
                new InterfaceMethodRefConstant(readShort(in), readShort(in));
            case 12 ->
                new NameAndTypeConstant(readShort(in), readShort(in));
            case 15 ->
                new MethodHandleConstant((byte) in.read(), readShort(in));
            case 16 ->
                new MethodTypeConstant(readShort(in));
            case 17 ->
                new DynamicConstant(readShort(in), readShort(in));
            case 18 ->
                new InvokeDynamicConstant(readShort(in), readShort(in));
            case 19 ->
                new ModuleConstant(readShort(in));
            case 20 ->
                new PackageConstant(readShort(in));
            default ->
                null;
        };
    }

    public static ClassFile readClassFile(DataInputStream r) throws IOException {
        final ClassFile ret = new ClassFile();
        if (!Arrays.equals(MAGIC, r.readNBytes(4))) {
            throw new IllegalArgumentException("Not a Java Class file");
        }

        ret.minorVersion = readShort(r);
        ret.majorVersion = new MajorVersion(readShort(r));

        final short constantPoolCount = readShort(r);
        ret.constantPool = new ConstantDesc[constantPoolCount];
        for (int i = 1; i < constantPoolCount; i++) {
            ret.constantPool[i] = readConstant(r);
        }

        ret.accessFlags = readShort(r);
        ret.thisClass = readShort(r);
        ret.superClass = readShort(r);

        final int interfacesCount = readShort(r);
        ret.interfaces = new short[interfacesCount];
        for (int i = 0; i < interfacesCount; i++) {
            ret.interfaces[i] = readShort(r);
        }

        final int fieldsCount = readShort(r);
        ret.fields = new FieldDesc[fieldsCount];
        for (int i = 0; i < fieldsCount; i++) {
            ret.fields[i] = FieldDesc.parse(r, ret);
        }

        final int methodsCount = readShort(r);
        ret.methods = new MethodDesc[methodsCount];
        for (int i = 0; i < methodsCount; i++) {
            ret.methods[i] = MethodDesc.parse(r, ret);
        }

        final int attributesCount = readShort(r);
        ret.attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            ret.attributes[i] = AttributeReader.read(r, ret);
        }

        if (r.read() != -1) {
            throw new RuntimeException("End of file not reached");
        }
        return ret;
    }
}
