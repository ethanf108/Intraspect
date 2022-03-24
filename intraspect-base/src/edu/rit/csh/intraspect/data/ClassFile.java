package edu.rit.csh.intraspect.data;

import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import edu.rit.csh.intraspect.data.attribute.AttributeReader;
import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.EmptyWideConstant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.ConstantPoolIndexedRecord;
import edu.rit.csh.intraspect.util.OffsetInputStream;
import edu.rit.csh.intraspect.util.OffsetOutputStream;
import edu.rit.csh.intraspect.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;

public class ClassFile {

    /**
     * The magic number present in the first 4 bytes of all valid class files.
     */
    private static final byte[] MAGIC = {(byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE};
    private int accessFlags;
    private int minorVersion;
    private MajorVersion majorVersion;
    private ConstantPool constantPool;

    @ConstantPoolIndex(ClassConstant.class)
    private int thisClass;

    @ConstantPoolIndex(ClassConstant.class)
    private int superClass;

    @ConstantPoolIndex(ClassConstant.class)
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
     * @param source the data input stream to read from
     * @return the class file read from the data input stream
     * @throws IOException if an I/O error occurs
     */
    public static ClassFile readClassFile(final InputStream source) throws IOException {
        final OffsetInputStream in = new OffsetInputStream(source);
        final ClassFile ret = new ClassFile();

        if (!Arrays.equals(MAGIC, in.readNBytes(4))) {
            throw new IllegalArgumentException("Not a Java Class file");
        }

        ret.minorVersion = in.readUnsignedShort();
        ret.majorVersion = new MajorVersion(in.readUnsignedShort());

        final int constantPoolCount = in.readUnsignedShort();
        ret.constantPool = new ConstantPool();
        for (int i = 1; i < constantPoolCount; i++) {
            final ConstantDesc cd = ConstantDesc.readConstant(in);
            ret.constantPool.addInternal(cd);
            if (cd.isWide()) {
                ret.constantPool.addInternal(new EmptyWideConstant());
                i++;
            }
        }
        if (constantPoolCount - 1 != ret.constantPool.getNumConstants()) {
            throw new IllegalStateException("Invalid number of Constants read");
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

    @SuppressWarnings("rawtypes")
    private static void recurseAddConstantResize(int index, int dif, Object obj) {
        final Class<?> clazz = obj.getClass();
        if (!clazz.getModule().equals(ClassFile.class.getModule())) {
            return;
        }
        for (final Field field : Util.getAllFields(obj.getClass())) {
            final Class<?> baseType = field.getType().isArray() ? field.getType().componentType() : field.getType();
            try {
                field.setAccessible(true);
                if (field.isAnnotationPresent(ConstantPoolIndex.class) && !obj.getClass().isRecord()) {
                    if (field.getType() == int[].class) {
                        int[] val = (int[]) field.get(obj);
                        for (int i = 0; i < val.length; i++) {
                            if (val[i] >= index) {
                                val[i] += dif;
                            }
                        }
                    } else if (field.getInt(obj) >= index) {
                        field.setInt(obj, field.getInt(obj) + dif);
                    }
                } else if (baseType.getModule().equals(ClassFile.class.getModule())) {
                    if (field.getType().isArray()) {
                        if (field.getType().getComponentType().isRecord() && ConstantPoolIndexedRecord.class.isAssignableFrom(field.getType().getComponentType())) {
                            Object[] na = (Object[]) Array.newInstance(field.getType().getComponentType(), ((Object[]) field.get(obj)).length);
                            for (int i = 0; i < na.length; i++) {
                                na[i] = ((ConstantPoolIndexedRecord) ((Object[]) field.get(obj))[i]).shift(index, dif);
                            }
                            field.set(obj, field.getType().cast(na));
                        }
                        for (Object o : (Object[]) field.get(obj)) {
                            recurseAddConstantResize(index, dif, o);
                        }
                    } else {
                        if (field.getType().isRecord() && ConstantPoolIndexedRecord.class.isAssignableFrom(field.getType())) {
                            field.set(obj, ((ConstantPoolIndexedRecord) field.get(obj)).shift(index, dif));
                        }
                        recurseAddConstantResize(index, dif, field.get(obj));
                    }
                }
            } catch (final IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public int getMinorVersion() {
        return this.minorVersion;
    }

    public int getThisClass() {
        return this.thisClass;
    }

    public int getSuperClass() {
        return this.superClass;
    }

    /**
     * Returns the constant descriptor at the given index.
     *
     * @param index the index of the constant descriptor
     * @return the constant descriptor at the given index
     */
    public ConstantDesc getConstantDesc(final int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Constant Pool entries are 1-indexed");
        }
        if (this.constantPool.get(index) instanceof EmptyWideConstant) {
            throw new IllegalArgumentException("Cannot index an Empty Wide Constant");
        }
        return this.constantPool.get(index);
    }

    public <T extends ConstantDesc> T getConstantDesc(final int index, final Class<T> clazz) {
        return clazz.cast(this.getConstantDesc(index));
    }

    public ConstantPool getConstantPool() {
        return this.constantPool;
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
        ConstantDesc[] ret = new ConstantDesc[this.constantPool.getNumConstants()];
        for (int i = 1; i <= this.constantPool.getNumConstants(); i++) {
            ret[i - 1] = this.constantPool.get(i);
        }
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
        final int[] ret = new int[this.interfaces.length];
        System.arraycopy(this.interfaces, 0, ret, 0, this.interfaces.length);
        return ret;
    }

    public void addConstant(final int index, final ConstantDesc cd) {
        if (index == 0) {
            throw new IllegalArgumentException("Constant Pool Entries are 1-indexed");
        }
        this.constantPool.addResize(index, cd);
        recurseAddConstantResize(index, 1, this);
    }

    public void removeConstant(final int index) {
        if (index == 0) {
            throw new IllegalArgumentException("Constant Pool Entries are 1-indexed");
        }
        this.constantPool.removeResize(index);
        recurseAddConstantResize(index, -1, this);
    }

    /**
     * Writes the class file to the given data output stream.
     *
     * @param stream the data output stream to write to
     * @throws IOException if an I/O error occurs
     */
    public void write(final OutputStream stream) throws IOException {
        final OffsetOutputStream out = new OffsetOutputStream(stream);

        out.write(MAGIC);
        out.writeShort(this.minorVersion);
        out.writeShort(this.majorVersion.getMajorVersion());

        this.constantPool.write(out);

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

    public boolean hasFlag(final AccessFlag flag) {
        return (this.accessFlags & flag.mask) > 0;
    }

    public Set<AccessFlag> getFlags() {
        final EnumSet<AccessFlag> ret = EnumSet.noneOf(AccessFlag.class);
        for (final AccessFlag flag : AccessFlag.values()) {
            if (this.hasFlag(flag)) {
                ret.add(flag);
            }
        }
        return ret;
    }

    public enum AccessFlag {
        PUBLIC(0x0001),
        FINAL(0x0010),
        SUPER(0x0020),
        INTERFACE(0x0200),
        ABSTRACT(0x0400),
        SYNTHETIC(0x1000),
        ANNOTATION(0x2000),
        ENUM(0x4000),
        MODULE(0x8000);

        public final int mask;

        AccessFlag(final int mask) {
            this.mask = mask;
        }
    }
}
