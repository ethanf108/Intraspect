package edu.rit.csh.intraspect.data;

import edu.rit.csh.intraspect.data.attribute.AttributeDesc;
import edu.rit.csh.intraspect.data.attribute.AttributeReader;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

/**
 * Describes a field in a class.
 */
public class FieldDesc {

    private final int accessFlags;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int nameIndex;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int descriptorIndex;

    private final AttributeDesc[] attributes;

    /**
     * Constructs a new FieldDesc.
     *
     * @param accessFlags     the access flags for this field
     * @param nameIndex       the name index for this field
     * @param descriptorIndex the descriptor index for this field
     * @param attributes      the attributes of this field
     */
    public FieldDesc(final int accessFlags, final int nameIndex, final int descriptorIndex, final AttributeDesc[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    /**
     * Reads and constructs a new FieldDesc from a DataInputStream.
     *
     * @param in  the DataInputStream to read from
     * @param ref the ConstantPool to use for reading
     * @return the new FieldDesc
     * @throws IOException if an I/O error occurs
     */
    public static FieldDesc parse(final DataInputStream in, final ClassFile ref) throws IOException {
        final int accessFlags = in.readUnsignedShort();
        final int nameIndex = in.readUnsignedShort();
        final int descIndex = in.readUnsignedShort();

        final AttributeDesc[] attributes = new AttributeDesc[in.readUnsignedShort()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new FieldDesc(accessFlags, nameIndex, descIndex, attributes);
    }

    /**
     * Returns the access flags for this field.
     *
     * @return the access flags for this field
     */
    public int getAccessFlags() {
        return this.accessFlags;
    }

    /**
     * Returns the name index of this field.
     *
     * @return the name index of this field
     */
    public int getNameIndex() {
        return this.nameIndex;
    }

    /**
     * Returns the descriptor index of this field.
     *
     * @return the descriptor index of this field
     */
    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    /**
     * Returns the attributes of this field.
     *
     * @return the attributes of this field
     */
    public AttributeDesc[] getAttributes() {
        return this.attributes;
    }

    /**
     * Writes this FieldDesc to a DataOutputStream.
     *
     * @param out the DataOutputStream to write to
     * @throws IOException if an I/O error occurs
     */
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.accessFlags);
        out.writeShort(this.nameIndex);
        out.writeShort(this.descriptorIndex);
        out.writeShort(this.attributes.length);
        for (final AttributeDesc a : this.attributes) {
            a.write(out);
        }
    }

    public boolean hasFlag(AccessFlag flag) {
        return (this.accessFlags & flag.mask) > 0;
    }

    public Set<AccessFlag> getFlags() {
        EnumSet<AccessFlag> ret = EnumSet.noneOf(AccessFlag.class);
        for (AccessFlag flag : AccessFlag.values()) {
            if (this.hasFlag(flag)) {
                ret.add(flag);
            }
        }
        return ret;
    }

    public enum AccessFlag {
        PUBLIC(0x0001),
        PRIVATE(0x0002),
        PROTECTED(0x0004),
        STATIC(0x0008),
        FINAL(0x0010),
        VOLATILE(0x0040),
        TRANSIENT(0x0080),
        SYNTHETIC(0x1000),
        ENUM(0x4000);

        public final int mask;

        AccessFlag(int mask) {
            this.mask = mask;
        }
    }
}
