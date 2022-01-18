package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("SourceDebugExtension")
public class SourceDebugExtensionAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final byte[] debugExtension;

    public SourceDebugExtensionAttribute(int attributeNameIndex, byte[] debugExtension) {
        this.attributeNameIndex = attributeNameIndex;
        this.debugExtension = debugExtension;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return this.debugExtension.length;
    }

    public byte[] getDebugExtension() {
        return debugExtension;
    }

    public static SourceDebugExtensionAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        final byte[] data = new byte[length];
        in.read(data, 0, length);
        return new SourceDebugExtensionAttribute(ani, data);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.debugExtension.length);
        out.write(this.debugExtension);
    }

}
