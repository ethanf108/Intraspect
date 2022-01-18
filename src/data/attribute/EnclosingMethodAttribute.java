package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import static util.Util.*;

@AttributeName("EnclosingMethod")
public class EnclosingMethodAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 4;

    private final int attributeNameIndex;
    private final int classIndex;
    private final int methodIndex;

    private EnclosingMethodAttribute(final int attributeNameIndex, final int classIndex, final int methodIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.classIndex = classIndex;
        this.methodIndex = methodIndex;
    }

    public static EnclosingMethodAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new EnclosingMethodAttribute(ani, in.readUnsignedShort(), in.readUnsignedShort());
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    public int getMethodIndex() {
        return this.methodIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, this.classIndex);
        writeShort(out, this.methodIndex);
    }

    @Override
    public int getDataLength() {
        return ATTRIBUTE_LENGTH;
    }
}
