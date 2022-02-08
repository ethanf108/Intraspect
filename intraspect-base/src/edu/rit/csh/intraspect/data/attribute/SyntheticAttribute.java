package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Synthetic attribute.
 */
@AttributeName("Synthetic")
public class SyntheticAttribute implements AttributeDesc {

    private final int attributeNameIndex;

    private SyntheticAttribute(final int ani) {
        this.attributeNameIndex = ani;
    }

    public static SyntheticAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != 0) {
            throw new IllegalArgumentException("Synthetic Attribute Length must be 0");
        }
        return new SyntheticAttribute(ani);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 0;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
    }
}
