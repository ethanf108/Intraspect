package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("ModuleMainClass")
public class ModuleMainClassAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 2;

    private final short attributeNameIndex;
    private final short mainClassIndex;

    private ModuleMainClassAttribute(final short attributeNameIndex, final short mainClassIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.mainClassIndex = mainClassIndex;
    }

    public static ModuleMainClassAttribute read(final short ani, final InputStream in) throws IOException {
        if (readInt(in) != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new ModuleMainClassAttribute(ani, readShort(in));
    }

    public short getMainClassIndex() {
        return this.mainClassIndex;
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, ATTRIBUTE_LENGTH);
        writeShort(out, this.mainClassIndex);
    }

    @Override
    public int getDataLength() {
        return ATTRIBUTE_LENGTH;
    }
}
