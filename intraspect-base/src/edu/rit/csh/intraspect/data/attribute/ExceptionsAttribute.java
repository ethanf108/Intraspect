package edu.rit.csh.intraspect.data.attribute;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The Exceptions attribute.
 */
@AttributeName("Exceptions")
public final class ExceptionsAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int[] exceptionIndexTable;

    private ExceptionsAttribute(final int ani, final int[] eit) {
        this.attributeNameIndex = ani;
        this.exceptionIndexTable = eit;
    }

    public static ExceptionsAttribute read(final int ani, final DataInputStream in) throws IOException {

        in.readInt();   // Ignore

        final int[] exceptions = new int[in.readUnsignedShort()];

        for (int i = 0; i < exceptions.length; i++) {
            exceptions[i] = in.readUnsignedShort();
        }

        return new ExceptionsAttribute(ani, exceptions);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int[] getExceptionIndexTable() {
        return this.exceptionIndexTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.exceptionIndexTable.length);
        for (int s : this.exceptionIndexTable) {
            out.writeShort(s);
        }
    }

    @Override
    public int getDataLength() {
        return this.exceptionIndexTable.length * 2 + 2;
    }
}
