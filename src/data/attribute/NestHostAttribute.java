package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

@AttributeName("NestHost")
public class NestHostAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int hostClassIndex;

    public NestHostAttribute(int attributeNameIndex, int hostClassIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.hostClassIndex = hostClassIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public int getHostClassIndex() {
        return hostClassIndex;
    }

    public static NestHostAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();    // Ignore
        if (length != 2) {
            throw new IllegalArgumentException("Nest Host Attribute Length must be 2");
        }
        final int hostClass = in.readUnsignedShort();
        return new NestHostAttribute(ani, hostClass);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, this.hostClassIndex);
    }

    @Override
    public int getDataLength() {
        return 2;
    }
}
