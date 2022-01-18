package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.hostClassIndex);
    }

    @Override
    public int getDataLength() {
        return 2;
    }
}
