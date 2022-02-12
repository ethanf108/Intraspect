package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Deprecated attribute.
 */
@AttributeName("Deprecated")
public final class DeprecatedAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    public DeprecatedAttribute(int attributeNameIndex) {
        this.attributeNameIndex = attributeNameIndex;
    }

    public static DeprecatedAttribute read(int ani, DataInputStream in) throws IOException {
        if (in.readInt() != 0) {
            throw new IllegalArgumentException("Deprecated Attribute Length must be 0");
        }
        return new DeprecatedAttribute(ani);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
    }

    @Override
    public int getDataLength() {
        return 0;
    }
}
