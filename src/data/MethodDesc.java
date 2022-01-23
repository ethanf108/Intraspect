package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * This class represents a method descriptor.*
 */
public class MethodDesc {

    private final int accessFlags;
    private final int nameIndex;
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
}
