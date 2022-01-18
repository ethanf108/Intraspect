package data;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FieldDesc {

    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final AttributeDesc[] attributes;

    public FieldDesc(int accessFlags, int nameIndex, int descriptorIndex, AttributeDesc[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }

    public AttributeDesc[] getAttributes() {
        return attributes;
    }

    public static FieldDesc parse(DataInputStream in, ClassFile ref) throws IOException {
        final int accessFlags = in.readUnsignedShort();
        final int nameIndex = in.readUnsignedShort();
        final int descIndex = in.readUnsignedShort();
        final int attributesCount = in.readUnsignedShort();
        final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new FieldDesc(accessFlags, nameIndex, descIndex, attributes);
    }

    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.accessFlags);
        out.writeShort(this.nameIndex);
        out.writeShort(this.descriptorIndex);
        out.writeShort(this.attributes.length);
        for (AttributeDesc a : this.attributes) {
            a.write(out);
        }
    }
}
