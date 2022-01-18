package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("LocalVariableTable")
public class LocalVariableTableAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final LocalVariableTableEntry[] localVariableTable;

    public LocalVariableTableAttribute(final short attributeNameIndex, final LocalVariableTableEntry[] localVariableTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.localVariableTable = localVariableTable;
    }

    public static LocalVariableTableAttribute read(final short ani, final DataInputStream in) throws IOException {
        readInt(in);    // Discard attribute length

        final LocalVariableTableEntry[] arr = new LocalVariableTableEntry[readShort(in)];
        for (int i = 0; i < arr.length; arr[i++] = new LocalVariableTableEntry(readShort(in), readShort(in), readShort(in), readShort(in), readShort(in)))
            ;

        return new LocalVariableTableAttribute(ani, arr);
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public short getLineNumberTableLength() {
        return (short) this.localVariableTable.length;
    }

    @Override

    public int getDataLength() {
        return getLineNumberTableLength() * 10 + 2;
    }

    public LocalVariableTableEntry[] getLocalVariableTable() {
        return this.localVariableTable;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, getLineNumberTableLength());

        for (final LocalVariableTableEntry entry : localVariableTable) {
            writeShort(out, entry.startPc);
            writeShort(out, entry.length);
            writeShort(out, entry.nameIndex);
            writeShort(out, entry.descriptorIndex);
            writeShort(out, entry.index);
        }
    }

    private static record LocalVariableTableEntry(short startPc, short length, short nameIndex, short descriptorIndex, short index) {

    }
}
