package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static util.Util.*;

@AttributeName("SourceFile")
public class SourceFileAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 2;
    private final short attributeNameIndex;
    private final short sourcefileIndex;

    private SourceFileAttribute(final short attributeNameIndex, final short sourcefileIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.sourcefileIndex = sourcefileIndex;
    }

    public static SourceFileAttribute read(final short ani, final InputStream in) throws IOException {
        if (readInt(in) != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + ATTRIBUTE_LENGTH);
        }
        return new SourceFileAttribute(ani, readShort(in));
    }

    public short getSourcefileIndex() {
        return this.sourcefileIndex;
    }

    @Override
    public short getAttributeNameIndex() {
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