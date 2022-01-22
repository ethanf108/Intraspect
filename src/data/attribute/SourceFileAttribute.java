package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("SourceFile")
public class SourceFileAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 2;
    private final int attributeNameIndex;
    private final int sourceFileIndex;

    private SourceFileAttribute(final int attributeNameIndex, final int sourceFileIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.sourceFileIndex = sourceFileIndex;
    }

    public static SourceFileAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != ATTRIBUTE_LENGTH) {
            throw new IllegalArgumentException("Enclosing Method Attribute length must be " + SourceFileAttribute.ATTRIBUTE_LENGTH);
        }
        return new SourceFileAttribute(ani, in.readUnsignedShort());
    }

    public int getSourceFileIndex() {
        return this.sourceFileIndex;
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
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.sourceFileIndex);
    }
}
