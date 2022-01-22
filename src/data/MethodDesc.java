package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodDesc {

    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final AttributeDesc[] attributes;

    public MethodDesc(final int accessFlags, final int nameIndex, final int descriptorIndex, final AttributeDesc[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }

    public AttributeDesc[] getAttributes() {
        return this.attributes;
    }

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
