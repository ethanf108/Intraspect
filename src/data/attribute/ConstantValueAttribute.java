package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("ConstantValue")
public class ConstantValueAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int constantValueIndex;

    private ConstantValueAttribute(int attributeNameIndex, int constantValueIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }

    public static ConstantValueAttribute read(int ani, DataInputStream in) throws IOException {
        if (in.readInt() != 2) {
            throw new IllegalArgumentException("Constant Value Attribute length must be 2");
        }
        return new ConstantValueAttribute(ani, in.readUnsignedShort());
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.constantValueIndex);
    }

    @Override
    public int getDataLength() {
        return 2;
    }

}
