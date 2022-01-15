package data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readShort;
import static util.Util.writeShort;

public class FieldDesc {

    private final short accessFlags;
    private final short nameIndex;
    private final short descriptorIndex;
    private final AttributeDesc[] attributes;

    public FieldDesc(short accessFlags, short nameIndex, short descriptorIndex, AttributeDesc[] attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributes = attributes;
    }

    public short getAccessFlags() {
        return accessFlags;
    }

    public short getNameIndex() {
        return nameIndex;
    }

    public short getDescriptorIndex() {
        return descriptorIndex;
    }

    public AttributeDesc[] getAttributes() {
        return attributes;
    }

    public static FieldDesc parse(InputStream in, ClassFile ref) throws IOException {
        final short accessFlags = readShort(in);
        final short nameIndex = readShort(in);
        final short descIndex = readShort(in);
        final short attributesCount = readShort(in);
        final AttributeDesc[] attributes = new AttributeDesc[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = AttributeReader.read(in, ref);
        }
        return new FieldDesc(accessFlags, nameIndex, descIndex, attributes);
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
