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

@AttributeName("Exceptions")
public class ExceptionsAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final short[] exceptionIndexTable;

    private ExceptionsAttribute(short ani, short[] eit) {
        this.attributeNameIndex = ani;
        this.exceptionIndexTable = eit;
    }

    @Override
    public short getAttributeNameIndex() {
        return attributeNameIndex;
    }

    public short[] getExceptionIndexTable() {
        return exceptionIndexTable;
    }

    public static ExceptionsAttribute read(short ani, DataInputStream in) throws IOException {
        final int length = readInt(in);
        final int numExceptions = in.readUnsignedShort();
        final short[] exceptions = new short[numExceptions];
        for (int i = 0; i < numExceptions; i++) {
            exceptions[i] = in.readUnsignedShort();
        }
        return new ExceptionsAttribute(ani, exceptions);
    }

    @Override
    public void write(OutputStream out) throws IOException {
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
