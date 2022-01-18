package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.OutputStream;

import static util.Util.*;

@AttributeName("LineNumberTable")
public class LineNumberTableAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final LineNumberTableEntry[] lineNumberTable;

    private LineNumberTableAttribute(final short attributeNameIndex, final LineNumberTableEntry[] lineNumberTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.lineNumberTable = lineNumberTable;
    }

    public static LineNumberTableAttribute read(final short ani, final DataInputStream in) throws IOException {
        readInt(in);    // Discard attribute length

        final LineNumberTableEntry[] arr = new LineNumberTableEntry[readShort(in)];
        for (int i = 0; i < arr.length; arr[i++] = new LineNumberTableEntry(readShort(in), readShort(in))) ;

        return new LineNumberTableAttribute(ani, arr);
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public short getLineNumberTableLength() {
        return (short) this.lineNumberTable.length;
    }

    public LineNumberTableEntry[] getLineNumberTable() {
        return this.lineNumberTable;
    }

    @Override
    public void write(final OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, getLineNumberTableLength());

        for (LineNumberTableEntry entry : lineNumberTable) {
            writeShort(out, entry.startPc);
            writeShort(out, entry.lineNumber);
        }
    }

    @Override
    public int getDataLength() {
        return getLineNumberTableLength() * 4 + 2;
    }

    private static record LineNumberTableEntry(short startPc, short lineNumber) {

    }
}
