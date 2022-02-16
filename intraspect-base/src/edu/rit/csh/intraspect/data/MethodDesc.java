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
 * This class represents a method descriptor.*
 */
public class MethodDesc {

    private final int accessFlags;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int nameIndex;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int descriptorIndex;

    private final AttributeDesc[] attributes;

    /**
     * Constructs a new MethodDesc.
     *
     * @param accessFlags     the access flags of this MethodDesc.
     * @param nameIndex       the index of the name of this MethodDesc.
     * @param descriptorIndex the index of the descriptor of this MethodDesc.
     * @param attributes      the attributes of this MethodDesc.
     */
    public MethodDesc(final int accessFlags, final int nameIndex, final int descriptorIndex, final AttributeDesc[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    /**
     * Reads and constructs a new MethodDesc from a DataInputStream.
     *
     * @param in  the DataInputStream to read from.
     * @param ref the containing ClassFile to reference.
     * @return the new MethodDesc.
     * @throws IOException if an I/O error occurs.
     */
    public static MethodDesc parse(final DataInputStream in, final ClassFile ref) throws IOException {
        final int accessFlags = in.readUnsignedShort();
        final int nameIndex = in.readUnsignedShort();
        final int descIndex = in.readUnsignedShort();

        final AttributeDesc[] attributes = new AttributeDesc[in.readUnsignedShort()];
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new MethodDesc(accessFlags, nameIndex, descIndex, attributes);
    }

    public static int combineFlags(AccessFlag... flags) {
        int mask = 0;
        for (AccessFlag flag : flags) {
            mask |= flag.mask;
        }
        return mask;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    /**
     * Returns the index of the name of this MethodDesc.
     *
     * @return the index of the name of this MethodDesc.
     */
    public int getNameIndex() {
        return this.nameIndex;
    }

    /**
     * Returns the index of the descriptor of this MethodDesc.
     *
     * @return the index of the descriptor of this MethodDesc.
     */
    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    /**
     * Returns the attributes of this MethodDesc.
     *
     * @return the attributes of this MethodDesc.
     */
    public AttributeDesc[] getAttributes() {
        return this.attributes;
    }

    /**
     * Writes this MethodDesc to a DataOutputStream.
     *
     * @param out the DataOutputStream to write to.
     * @throws IOException if an I/O error occurs.
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
        SYNCHRONIZED(0x0020),
        BRIDGE(0x0040),
        VARARGS(0x0080),
        NATIVE(0x0100),
        ABSTRACT(0x0400),
        STRICT(0x0800),
        SYNTHETIC(0x1000);

        public final int mask;

        AccessFlag(int mask) {
            this.mask = mask;
        }
    }
}
