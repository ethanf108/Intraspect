package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The SourceFile attribute.
 */
@AttributeName("SourceFile")
public final class SourceFileAttribute implements AttributeDesc {

    private static final int ATTRIBUTE_LENGTH = 2;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    @ConstantPoolIndex(UTF8Constant.class)
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
        return SourceFileAttribute.ATTRIBUTE_LENGTH;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.sourceFileIndex);
    }
}
