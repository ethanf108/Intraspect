package data;

import data.constant.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ClassFile {

    private static final byte[] MAGIC = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};

    private int minorVersion;
    private MajorVersion majorVersion;
    private ConstantDesc[] constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int[] interfaces;
    private FieldDesc[] fields;
    private MethodDesc[] methods;
    private AttributeDesc[] attributes;

    private ClassFile() {

    }

    public ConstantDesc getConstantDesc(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Constant Pool entries are 1-indexed");
        }
        if (this.constantPool[index] instanceof EmptyWideConstant) {
            throw new IllegalArgumentException("Cannot index an Empty Wide Constant");
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

    public void write(DataOutputStream out) throws IOException {
        out.write(MAGIC);
        out.writeShort(this.minorVersion);
        out.writeShort(this.majorVersion.getMajorVersion());

        out.writeShort(this.constantPool.length);
        for (int i = 1; i < this.constantPool.length; i++) {
            this.constantPool[i].write(out);
        }

        out.writeShort(this.accessFlags);
        out.writeShort(this.thisClass);
        out.writeShort(this.superClass);

        out.writeShort(this.interfaces.length);
        for (int s : this.interfaces) {
            out.writeShort(s);
        }

        out.writeShort(this.fields.length);
        for (FieldDesc field : this.fields) {
            field.write(out);
        }

        out.writeShort(this.methods.length);
        for (MethodDesc method : this.methods) {
            method.write(out);
        }

        out.writeShort(this.attributes.length);
        for (AttributeDesc attr : this.attributes) {
            attr.write(out);
        }
        out.flush();
    }

    private static ConstantDesc readConstant(DataInputStream in) throws IOException {
        final int tag = in.readUnsignedByte();
        return switch (tag) {
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
                new ClassConstant(in.readUnsignedShort());
            case 8 ->
                new StringConstant(in.readUnsignedShort());
            case 9 ->
                new FieldRefConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 10 ->
                new MethodRefConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 11 ->
                new InterfaceMethodRefConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 12 ->
                new NameAndTypeConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 15 ->
                new MethodHandleConstant(in.readUnsignedByte(), in.readUnsignedShort());
            case 16 ->
                new MethodTypeConstant(in.readUnsignedShort());
            case 17 ->
                new DynamicConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 18 ->
                new InvokeDynamicConstant(in.readUnsignedShort(), in.readUnsignedShort());
            case 19 ->
                new ModuleConstant(in.readUnsignedShort());
            case 20 ->
                new PackageConstant(in.readUnsignedShort());
            default ->
                throw new IllegalArgumentException("Invalid Tag: " + tag);
        };
    }

    public static ClassFile readClassFile(DataInputStream in) throws IOException {
        final ClassFile ret = new ClassFile();
        if (!Arrays.equals(MAGIC, in.readNBytes(4))) {
            throw new IllegalArgumentException("Not a Java Class file");
        }

        ret.minorVersion = in.readUnsignedShort();
        ret.majorVersion = new MajorVersion(in.readUnsignedShort());

        final int constantPoolCount = in.readUnsignedShort();
        ret.constantPool = new ConstantDesc[constantPoolCount];
        for (int i = 1; i < constantPoolCount; i++) {
            ret.constantPool[i] = readConstant(in);
            if (ret.constantPool[i].isWide()) {
                ret.constantPool[++i] = new EmptyWideConstant();
            }
        }

        ret.accessFlags = in.readUnsignedShort();
        ret.thisClass = in.readUnsignedShort();
        ret.superClass = in.readUnsignedShort();

        final int interfacesCount = in.readUnsignedShort();
        ret.interfaces = new int[interfacesCount];
        for (int i = 0; i < interfacesCount; i++) {
            ret.interfaces[i] = in.readUnsignedShort();
        }

        final int fieldsCount = in.readUnsignedShort();
        ret.fields = new FieldDesc[fieldsCount];
        for (int i = 0; i < fieldsCount; i++) {
            ret.fields[i] = FieldDesc.parse(in, ret);
        }

        final int methodsCount = in.readUnsignedShort();
        ret.methods = new MethodDesc[methodsCount];
        for (int i = 0; i < methodsCount; i++) {
            ret.methods[i] = MethodDesc.parse(in, ret);
        }

        final int attributesCount = in.readUnsignedShort();
        ret.attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            ret.attributes[i] = AttributeReader.read(in, ret);
        }

        if (in.read() != -1) {
            throw new RuntimeException("End of file not reached");
        }
        return ret;
    }
}
