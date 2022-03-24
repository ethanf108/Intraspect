package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.ConstantPoolIndexedRecord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The LocalVariableTypeTable attribute.
 */
@AttributeName("LocalVariableTypeTable")
public final class LocalVariableTypeTableAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    private final LocalVariableTypeTableEntry[] localVariableTypeTable;

    private LocalVariableTypeTableAttribute(final int attributeNameIndex, final LocalVariableTypeTableEntry[] localVariableTypeTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.localVariableTypeTable = localVariableTypeTable;
    }

    public static LocalVariableTypeTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Discard attribute length

        final LocalVariableTypeTableEntry[] arr = new LocalVariableTypeTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = new LocalVariableTypeTableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort())) {
        }

        return new LocalVariableTypeTableAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getLocalVariableTypeTableLength() {
        return this.localVariableTypeTable.length;
    }

    @Override
    public int getDataLength() {
        return this.getLocalVariableTypeTableLength() * 10 + 2;
    }

    public LocalVariableTypeTableEntry[] getLocalVariableTypeTable() {
        return this.localVariableTypeTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.getLocalVariableTypeTableLength());

        for (final LocalVariableTypeTableEntry entry : this.localVariableTypeTable) {
            out.writeShort(entry.startPc);
            out.writeShort(entry.length);
            out.writeShort(entry.nameIndex);
            out.writeShort(entry.signatureIndex);
            out.writeShort(entry.index);
        }
    }

    public record LocalVariableTypeTableEntry(
            int startPc,
            int length,
            @ConstantPoolIndex(UTF8Constant.class) int nameIndex,
            @ConstantPoolIndex(UTF8Constant.class) int signatureIndex,
            int index
    ) implements ConstantPoolIndexedRecord<LocalVariableTypeTableEntry> {

        @Override
        public LocalVariableTypeTableEntry shift(int index, int delta) {
            return new LocalVariableTypeTableEntry(
                    this.startPc,
                    this.length,
                    this.nameIndex >= index ? this.nameIndex + delta : this.nameIndex,
                    this.signatureIndex >= index ? this.signatureIndex + delta : this.signatureIndex,
                    index
            );
        }
    }
}
