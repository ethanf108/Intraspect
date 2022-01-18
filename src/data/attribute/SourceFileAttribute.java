package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("SourceFile")
public class SourceFileAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 2;
    private final int attributeNameIndex;
    private final int sourcefileIndex;

    private SourceFileAttribute(final int attributeNameIndex, final int sourcefileIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.sourcefileIndex = sourcefileIndex;
    }

    public static SourceFileAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new SourceFileAttribute(ani, in.readUnsignedShort());
    }

    public int getSourcefileIndex() {
        return this.sourcefileIndex;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return ATTRIBUTE_LENGTH;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, ATTRIBUTE_LENGTH);
        writeShort(out, this.sourcefileIndex);
    }
}
