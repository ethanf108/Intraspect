package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.ConstantPoolIndexedRecord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The LocalVariableTable attribute.
 */
@AttributeName("LocalVariableTable")
public final class LocalVariableTableAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;
    private final LocalVariableTableEntry[] localVariableTable;

    public LocalVariableTableAttribute(final int attributeNameIndex, final LocalVariableTableEntry[] localVariableTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.localVariableTable = localVariableTable;
    }

    public static LocalVariableTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final LocalVariableTableEntry[] arr = new LocalVariableTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = LocalVariableTableEntry.read(in)) {
        }

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
        return this.getLineNumberTableLength() * 10 + 2;
    }

    public LocalVariableTableEntry[] getLocalVariableTable() {
        return this.localVariableTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.getLineNumberTableLength());

        for (final LocalVariableTableEntry entry : this.localVariableTable) {
            entry.write(out);
        }
    }

    public record LocalVariableTableEntry(
            int startPc,
            int length,
            @ConstantPoolIndex(UTF8Constant.class) int nameIndex,
            @ConstantPoolIndex(UTF8Constant.class) int descriptorIndex,
            int index
    ) implements ConstantPoolIndexedRecord<LocalVariableTableEntry> {

        public static LocalVariableTableEntry read(final DataInputStream in) throws IOException {
            return new LocalVariableTableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.startPc);
            out.writeShort(this.length);
            out.writeShort(this.nameIndex);
            out.writeShort(this.descriptorIndex);
            out.writeShort(this.index);
        }

        @Override
        public LocalVariableTableEntry shift(int index, int delta) {
            return new LocalVariableTableEntry(
                    this.startPc,
                    this.length,
                    this.nameIndex >= index ? this.nameIndex + delta : this.nameIndex,
                    this.descriptorIndex >= index ? this.descriptorIndex + delta : this.descriptorIndex,
                    index
            );
        }
    }
}
