package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Deprecated")
public class DeprecatedAttribute implements AttributeDesc {

    private final short attributeNameIndex;

    public DeprecatedAttribute(short attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public static DeprecatedAttribute read(short ani, InputStream in) throws IOException {
        final int length = readInt(in);
        if (length != 0) {
            throw new IllegalArgumentException("Deprecated Attribute Length must be 0");
        }
        return new DeprecatedAttribute(ani);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
    }

    @Override
    public int getDataLength() {
        return 0;
    }
}
