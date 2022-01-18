package data.attribute;

import data.AttributeDesc;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

public class UnknownAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final byte[] data;

    private UnknownAttribute(int ani, byte[] data) {
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

    public static UnknownAttribute read(int attributeNameIndex, DataInputStream in) throws IOException {
        final int length = in.readInt();
        return new UnknownAttribute(attributeNameIndex, in.readNBytes(length));
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.data.length);
        out.write(this.data);
    }
}
