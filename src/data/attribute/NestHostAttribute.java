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

    public NestHostAttribute(final int attributeNameIndex, final int hostClassIndex) {
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

    public static NestHostAttribute read(final int ani, final DataInputStream in) throws IOException {
        final int length = in.readInt();    // Ignore
        if (length != 2) {
            throw new IllegalArgumentException("Nest Host Attribute Length must be 2");
        }

        return new NestHostAttribute(ani, in.readUnsignedShort());
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.hostClassIndex);
    }

    @Override
    public int getDataLength() {
        return 2;
    }
}
