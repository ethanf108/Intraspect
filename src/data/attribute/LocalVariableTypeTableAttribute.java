package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("LocalVariableTypeTable")
public class LocalVariableTypeTableAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final LocalVariableTypeTableEntry[] localVariableTypeTable;

    private LocalVariableTypeTableAttribute(final short attributeNameIndex, final LocalVariableTypeTableEntry[] localVariableTypeTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.localVariableTypeTable = localVariableTypeTable;
    }

    public static LocalVariableTypeTableAttribute read(final short ani, final InputStream in) throws IOException {
        readInt(in);    // Discard attribute length

        final LocalVariableTypeTableEntry[] arr = new LocalVariableTypeTableEntry[readShort(in)];
        for (int i = 0; i < arr.length; arr[i++] = new LocalVariableTypeTableEntry(readShort(in), readShort(in), readShort(in), readShort(in), readShort(in)))
            ;

        return new LocalVariableTypeTableAttribute(ani, arr);
    }

    @Override
    public short getAttributeNameIndex() {
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
    public void write(final OutputStream out) throws IOException {
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

    private static record LocalVariableTypeTableEntry(short startPc, short length, short nameIndex,
            short signatureIndex, short index) {

    }
}
