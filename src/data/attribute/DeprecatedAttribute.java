package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Deprecated")
public class DeprecatedAttribute implements AttributeDesc {

    private final int attributeNameIndex;

    public DeprecatedAttribute(int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public static DeprecatedAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        if (length != 0) {
            throw new IllegalArgumentException("Deprecated Attribute Length must be 0");
        }
        return new DeprecatedAttribute(ani);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
    }

    @Override
    public int getDataLength() {
        return 0;
    }
}
