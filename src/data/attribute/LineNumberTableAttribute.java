package data.attribute;

import data.AttributeDesc;
import data.AttributeName;

import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import static util.Util.*;

@AttributeName("LineNumberTable")
public class LineNumberTableAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final LineNumberTableEntry[] lineNumberTable;

    private LineNumberTableAttribute(final int attributeNameIndex, final LineNumberTableEntry[] lineNumberTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.lineNumberTable = lineNumberTable;
    }

    public static LineNumberTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final LineNumberTableEntry[] arr = new LineNumberTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = new LineNumberTableEntry(in.readUnsignedShort(), in.readUnsignedShort())) ;

        return new LineNumberTableAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getLineNumberTableLength() {
        return (short) this.lineNumberTable.length;
    }

    public LineNumberTableEntry[] getLineNumberTable() {
        return this.lineNumberTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
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

    private static record LineNumberTableEntry(int startPc, int lineNumber) {

    }
}
