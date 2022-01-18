package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("SourceDebugExtension")
public class SourceDebugExtensionAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final byte[] debugExtension;

    public SourceDebugExtensionAttribute(short attributeNameIndex, byte[] debugExtension) {
        this.attributeNameIndex = attributeNameIndex;
        this.debugExtension = debugExtension;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return this.debugExtension.length;
    }

    public byte[] getDebugExtension() {
        return debugExtension;
    }

    public static SourceDebugExtensionAttribute read(short ani, DataInputStream in) throws IOException {
        final int length = readInt(in);
        final byte[] data = new byte[length];
        in.read(data, 0, length);
        return new SourceDebugExtensionAttribute(ani, data);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.debugExtension.length);
        out.write(this.debugExtension);
    }

}
