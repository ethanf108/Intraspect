package data;

import java.io.IOException;
import java.io.InputStream;

public class FieldDesc {

    private final short accessFlags;
    private final short nameIndex;
    private final short descriptorIndex;
    private final Attribute[] attributes;

    public FieldDesc(short accessFlags, short nameIndex, short descriptorIndex, Attribute[] attributes) {
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

    public Attribute[] getAttributes() {
        return attributes;
    }

    public static FieldDesc parse(InputStream in) throws IOException {
        final short accessFlags = ClassFile.readShort(in);
        final short nameIndex = ClassFile.readShort(in);
        final short descIndex = ClassFile.readShort(in);
        final short attributesCount = ClassFile.readShort(in);
        final Attribute[] attributes = new Attribute[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = Attribute.parse(in);
        }
        return new FieldDesc(accessFlags, nameIndex, descIndex, attributes);
    }

}
