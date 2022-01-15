package data.attribute;

import data.AttributeDesc;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.writeInt;
import static util.Util.writeShort;

public class UnknownAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final byte[] data;

    private UnknownAttribute(short ani, byte[] data) {
        this.attributeNameIndex = ani;
        this.data = data;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public byte[] getData() {
        byte[] ret = new byte[this.data.length];
        System.arraycopy(this.data, 0, ret, 0, this.data.length);
        return ret;
    }

    public static UnknownAttribute read(short attributeNameIndex, InputStream in) throws IOException {
        final int length = readInt(in);
        return new UnknownAttribute(attributeNameIndex, in.readNBytes(length));
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.data.length);
        out.write(this.data);
    }
}
