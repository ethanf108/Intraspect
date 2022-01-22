package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("Exceptions")
public class ExceptionsAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int[] exceptionIndexTable;

    private ExceptionsAttribute(final int ani, final int[] eit) {
        this.attributeNameIndex = ani;
        this.exceptionIndexTable = eit;
    }

    @Override
    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public int[] getExceptionIndexTable() {
        return exceptionIndexTable;
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
