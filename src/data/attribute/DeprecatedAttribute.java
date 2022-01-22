package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

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
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
    }

    @Override
    public int getDataLength() {
        return 0;
    }
}
