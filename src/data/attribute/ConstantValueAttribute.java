package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("ConstantValue")
public class ConstantValueAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short constantValueIndex;

    private ConstantValueAttribute(short attributeNameIndex, short constantValueIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.constantValueIndex = constantValueIndex;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public short getConstantValueIndex() {
        return constantValueIndex;
    }

    public static ConstantValueAttribute read(short ani, InputStream in) throws IOException {
        if (readInt(in) != 2) {
            throw new IllegalArgumentException("Constant Value Attribute length must be 2");
        }
        return new ConstantValueAttribute(ani, readShort(in));
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, 2);
        writeShort(out, this.constantValueIndex);
    }

}
