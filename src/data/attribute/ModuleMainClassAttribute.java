package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import static util.Util.*;

@AttributeName("ModuleMainClass")
public class ModuleMainClassAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 2;

    private final int attributeNameIndex;
    private final int mainClassIndex;

    private ModuleMainClassAttribute(final int attributeNameIndex, final int mainClassIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.mainClassIndex = mainClassIndex;
    }

    public static ModuleMainClassAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new ModuleMainClassAttribute(ani, in.readUnsignedShort());
    }

    public int getMainClassIndex() {
        return this.mainClassIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, ATTRIBUTE_LENGTH);
        writeShort(out, this.mainClassIndex);
    }

    @Override
    public int getDataLength() {
        return ATTRIBUTE_LENGTH;
    }
}
