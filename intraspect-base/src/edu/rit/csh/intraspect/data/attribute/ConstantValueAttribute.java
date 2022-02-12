package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The ConstantValue attribute.
 */
@AttributeName("ConstantValue")
public final class ConstantValueAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int constantValueIndex;

    private ConstantValueAttribute(final int attributeNameIndex, final int constantValueIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.constantValueIndex = constantValueIndex;
    }

    public static ConstantValueAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != 2) {
            throw new IllegalArgumentException("Constant Value Attribute length must be 2");
        }
        return new ConstantValueAttribute(ani, in.readUnsignedShort());
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getConstantValueIndex() {
        return this.constantValueIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.constantValueIndex);
    }

    @Override
    public int getDataLength() {
        return 2;
    }

}
