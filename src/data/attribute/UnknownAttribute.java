package data.attribute;

import data.AttributeDesc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UnknownAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final byte[] data;

    private UnknownAttribute(final int ani, final byte[] data) {
        this.attributeNameIndex = ani;
        this.data = data;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return this.data.length;
    }

    public byte[] getData() {
        byte[] ret = new byte[this.data.length];
        System.arraycopy(this.data, 0, ret, 0, this.data.length);
        return ret;
    }

    public static UnknownAttribute read(final int attributeNameIndex, final DataInputStream in) throws IOException {
        final int length = in.readInt();
        return new UnknownAttribute(attributeNameIndex, in.readNBytes(length));
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.write(this.data);
    }
}
