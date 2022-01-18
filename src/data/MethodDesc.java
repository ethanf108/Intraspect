package data;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeShort;

public class MethodDesc {

    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final AttributeDesc[] attributes;

    public MethodDesc(int accessFlags, int nameIndex, int descriptorIndex, AttributeDesc[] attributes) {
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

    public static MethodDesc parse(DataInputStream in, ClassFile ref) throws IOException {
        final int accessFlags = in.readUnsignedShort();
        final int nameIndex = in.readUnsignedShort();
        final int descIndex = in.readUnsignedShort();
        final int attributesCount = in.readUnsignedShort();
        final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new MethodDesc(accessFlags, nameIndex, descIndex, attributes);
    }

    public void write(OutputStream out) throws IOException {
        writeShort(out, this.accessFlags);
        writeShort(out, this.nameIndex);
        writeShort(out, this.descriptorIndex);
        writeShort(out, (short) this.attributes.length);
        for (AttributeDesc a : this.attributes) {
            a.write(out);
        }
    }
}
