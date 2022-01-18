package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.DataOutputStream;
import static util.Util.writeInt;
import static util.Util.writeShort;

@AttributeName("Exceptions")
public class ExceptionsAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final int[] exceptionIndexTable;

    private ExceptionsAttribute(int ani, int[] eit) {
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

    public static ExceptionsAttribute read(int ani, DataInputStream in) throws IOException {
        final int length = in.readInt();
        final int numExceptions = in.readUnsignedShort();
        final int[] exceptions = new int[numExceptions];
        for (int i = 0; i < numExceptions; i++) {
            exceptions[i] = in.readUnsignedShort();
        }
        return new ExceptionsAttribute(ani, exceptions);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, (short) this.exceptionIndexTable.length);
        for (short s : this.exceptionIndexTable) {
            writeShort(out, s);
        }
    }

    @Override
    public int getDataLength() {
        return this.exceptionIndexTable.length * 2 + 2;
    }
}
