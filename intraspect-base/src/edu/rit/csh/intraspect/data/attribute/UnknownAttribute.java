package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Unknown attribute, used to represent all unknown attributes.
 */
public class UnknownAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final byte[] data;

    private UnknownAttribute(final int ani, final byte[] data) {
        this.attributeNameIndex = ani;
        this.data = data;
    }

    public static UnknownAttribute read(final int attributeNameIndex, final DataInputStream in) throws IOException {
        return new UnknownAttribute(attributeNameIndex, in.readNBytes(in.readInt()));
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return this.data.length;
    }

    public byte[] getData() {
        final byte[] ret = new byte[this.data.length];
        System.arraycopy(this.data, 0, ret, 0, this.data.length);
        return ret;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.write(this.data);
    }
}
