package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Signature")
public class SignatureAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int signatureIndex;

    private SignatureAttribute(int ani, int si) {
        this.attributeNameIndex = ani;
        this.signatureIndex = si;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    public int getSignatureIndex() {
        return signatureIndex;
    }

    public static SignatureAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        if (length != 2) {
            throw new IllegalArgumentException("Signature Attribute Length must be 2");
        }
        final int signatureIndex = in.readUnsignedShort();
        return new SignatureAttribute(ani, signatureIndex);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, 2);
        writeShort(out, this.signatureIndex);
    }
}
