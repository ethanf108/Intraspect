package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("LocalVariableTable")
public class LocalVariableTableAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final LocalVariableTableEntry[] localVariableTable;

    public LocalVariableTableAttribute(final int attributeNameIndex, final LocalVariableTableEntry[] localVariableTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.localVariableTable = localVariableTable;
    }

    public static LocalVariableTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final LocalVariableTableEntry[] arr = new LocalVariableTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = new LocalVariableTableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort()))
            ;

        return new LocalVariableTableAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getLineNumberTableLength() {
        return this.localVariableTable.length;
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

    private static record LocalVariableTableEntry(int startPc, int length, int nameIndex, int descriptorIndex, int index) {

    }
}
