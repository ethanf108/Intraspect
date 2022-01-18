package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import static util.Util.*;

@AttributeName("LocalVariableTypeTable")
public class LocalVariableTypeTableAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final LocalVariableTypeTableEntry[] localVariableTypeTable;

    private LocalVariableTypeTableAttribute(final int attributeNameIndex, final LocalVariableTypeTableEntry[] localVariableTypeTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.localVariableTypeTable = localVariableTypeTable;
    }

    public static LocalVariableTypeTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();   // Discard attribute length

        final LocalVariableTypeTableEntry[] arr = new LocalVariableTypeTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = new LocalVariableTypeTableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort()))
            ;

        return new LocalVariableTypeTableAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public short getLocalVariableTypeTableLength() {
        return (short) this.localVariableTypeTable.length;
    }

    @Override
    public int getDataLength() {
        return getLocalVariableTypeTableLength() * 10 + 2;
    }

    public LocalVariableTypeTableEntry[] getLocalVariableTypeTable() {
        return this.localVariableTypeTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, getLocalVariableTypeTableLength());

        for (final LocalVariableTypeTableEntry entry : localVariableTypeTable) {
            writeShort(out, entry.startPc);
            writeShort(out, entry.length);
            writeShort(out, entry.nameIndex);
            writeShort(out, entry.signatureIndex);
            writeShort(out, entry.index);
        }
    }

    private static record LocalVariableTypeTableEntry(int startPc, int length, int nameIndex,
            int signatureIndex, int index) {

    }
}
