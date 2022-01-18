package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.readInt;
import static util.Util.readShort;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Signature")
public class SignatureAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short signatureIndex;

    private SignatureAttribute(short ani, short si) {
        this.attributeNameIndex = ani;
        this.signatureIndex = si;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2;
    }

    public short getSignatureIndex() {
        return signatureIndex;
    }

    public static SignatureAttribute read(short ani, DataInputStream in) throws IOException {
        final int length = readInt(in);
        if (length != 2) {
            throw new IllegalArgumentException("Signature Attribute Length must be 2");
        }
        final short signatureIndex = readShort(in);
        return new SignatureAttribute(ani, signatureIndex);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, 2);
        writeShort(out, this.signatureIndex);
    }
}
