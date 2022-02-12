package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The LineNumberTable attribute.
 */
@AttributeName("LineNumberTable")
public final class LineNumberTableAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    private final LineNumberTableEntry[] lineNumberTable;

    private LineNumberTableAttribute(final int attributeNameIndex, final LineNumberTableEntry[] lineNumberTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.lineNumberTable = lineNumberTable;
    }

    public static LineNumberTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final LineNumberTableEntry[] arr = new LineNumberTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = new LineNumberTableEntry(in.readUnsignedShort(), in.readUnsignedShort())) {
        }

        return new LineNumberTableAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getLineNumberTableLength() {
        return this.lineNumberTable.length;
    }

    public LineNumberTableEntry[] getLineNumberTable() {
        return this.lineNumberTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.getLineNumberTableLength());

        for (final LineNumberTableEntry entry : this.lineNumberTable) {
            out.writeShort(entry.startPc);
            out.writeShort(entry.lineNumber);
        }
    }

    @Override
    public int getDataLength() {
        return this.getLineNumberTableLength() * 4 + 2;
    }

    public record LineNumberTableEntry(int startPc, int lineNumber) {

    }
}
