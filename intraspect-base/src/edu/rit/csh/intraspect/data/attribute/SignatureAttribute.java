package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Signature attribute.
 */
@AttributeName("Signature")
public final class SignatureAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    @ConstantPoolIndex(UTF8Constant.class)
    private final int signatureIndex;

    private SignatureAttribute(final int attributeNameIndex, final int signatureIndex) {
        this.attributeNameIndex = attributeNameIndex;
        this.signatureIndex = signatureIndex;
    }

    public static SignatureAttribute read(final int ani, final DataInputStream in) throws IOException {
        if (in.readInt() != 2) {
            throw new IllegalArgumentException("Signature Attribute Length must be 2");
        }

        return new SignatureAttribute(ani, in.readUnsignedShort());
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    public int getSignatureIndex() {
        return this.signatureIndex;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(2);
        out.writeShort(this.signatureIndex);
    }
}
