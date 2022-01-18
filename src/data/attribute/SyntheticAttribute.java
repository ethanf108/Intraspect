package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Synthetic")
public class SyntheticAttribute implements AttributeDesc {

    private final short attributeNameIndex;

    private SyntheticAttribute(short ani) {
        this.attributeNameIndex = ani;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 0;
    }

    public static SyntheticAttribute read(short ani, DataInputStream in) throws IOException {
        if (readInt(in) != 0) {
            throw new IllegalArgumentException("Synthetic Attribute Length must be 0");
        }
        return new SyntheticAttribute(ani);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, 0);
    }
}
