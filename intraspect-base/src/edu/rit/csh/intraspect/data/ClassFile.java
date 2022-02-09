package edu.rit.csh.intraspect.data;

import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import edu.rit.csh.intraspect.data.attribute.AttributeReader;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.EmptyWideConstant;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ClassFile {

    /**
     * The magic number present in the first 4 bytes of all valid class files.
     */
    private static final byte[] MAGIC = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};
    private int accessFlags;
    private int minorVersion;
    private MajorVersion majorVersion;
    private ConstantDesc[] constantPool;
    private int thisClass;
    private int superClass;
    private int[] interfaces;
    private FieldDesc[] fields;
    private MethodDesc[] methods;
    private AttributeDesc[] attributes;

    /**
     * Private constructor to prevent instantiation.
     */
    private ClassFile() {

    }

    /**
     * Reads a class file from the given data input stream.
     *
     * @param in the data input stream to read from
     * @return the class file read from the data input stream
     * @throws IOException if an I/O error occurs
     */
    public static ClassFile readClassFile(final DataInputStream in) throws IOException {
        final ClassFile ret = new ClassFile();
        if (!Arrays.equals(MAGIC, in.readNBytes(4))) {
            throw new IllegalArgumentException("Not a Java Class file");
        }

        ret.minorVersion = in.readUnsignedShort();
        ret.majorVersion = new MajorVersion(in.readUnsignedShort());

        final int constantPoolCount = in.readUnsignedShort();
        ret.constantPool = new ConstantDesc[constantPoolCount];
        for (int i = 1; i < constantPoolCount; i++) {
            ret.constantPool[i] = ConstantDesc.readConstant(in);
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

    /**
     * Returns the constant descriptor at the given index.
     *
     * @param index the index of the constant descriptor
     * @return the constant descriptor at the given index
     */
    public ConstantDesc getConstantDesc(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Constant Pool entries are 1-indexed");
        }
        if (this.constantPool[index] instanceof EmptyWideConstant) {
            throw new IllegalArgumentException("Cannot index an Empty Wide Constant");
        }
        return this.constantPool[index];
    }

    /**
     * Returns the major version of the class file.
     *
     * @return the major version of the class file
     */
    public MajorVersion getMajorVersion() {
        return this.majorVersion;
    }

    /**
     * Returns the constant pool of the class file.
     *
     * @return the constant pool of the class file
     */
    public ConstantDesc[] getConstants() {
        ConstantDesc[] ret = new ConstantDesc[this.constantPool.length - 1];
        System.arraycopy(this.constantPool, 1, ret, 0, this.constantPool.length - 1);
        return ret;
    }

    /**
     * Returns the attributes of the class file.
     *
     * @return the attributes of the class file
     */
    public AttributeDesc[] getAttributes() {
        return this.attributes;
    }

    /**
     * Returns the fields of the class file.
     *
     * @return the fields of the class file
     */
    public FieldDesc[] getFields() {
        return this.fields;
    }

    /**
     * Returns the methods of the class file.
     *
     * @return the methods of the class file
     */
    public MethodDesc[] getMethods() {
        return this.methods;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public int getSuperClassIndex() {
        return this.superClass;
    }

    public int[] getInterfaces() {
        int[] ret = new int[this.interfaces.length];
        System.arraycopy(this.interfaces, 0, ret, 0, this.interfaces.length);
        return ret;
    }

    /**
     * Writes the class file to the given data output stream.
     *
     * @param out the data output stream to write to
     * @throws IOException if an I/O error occurs
     */
    public void write(final DataOutputStream out) throws IOException {
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
        for (final int s : this.interfaces) {
            out.writeShort(s);
        }

        out.writeShort(this.fields.length);
        for (final FieldDesc field : this.fields) {
            field.write(out);
        }

        out.writeShort(this.methods.length);
        for (final MethodDesc method : this.methods) {
            method.write(out);
        }

        out.writeShort(this.attributes.length);
        for (final AttributeDesc attr : this.attributes) {
            attr.write(out);
        }
        out.flush();
    }

    public int getThisClassIndex() {
        return this.thisClass;
    }
}
