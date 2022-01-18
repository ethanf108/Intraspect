package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("InnerClasses")
public class InnerClassesAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final InnerClassesTableEntry[] innerClassesTable;

    private InnerClassesAttribute(final int attributeNameIndex, final InnerClassesTableEntry[] innerClassesTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.innerClassesTable = innerClassesTable;
    }

    public static InnerClassesAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final InnerClassesTableEntry[] arr = new InnerClassesTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = new InnerClassesTableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort()))
            ;

        return new InnerClassesAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2 + getInnerClassesTableLength() * 8;
    }

    public int getInnerClassesTableLength() {
        return this.innerClassesTable.length;
    }

    public InnerClassesTableEntry[] getInnerClassesTable() {
        return this.innerClassesTable;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(getInnerClassesTableLength());

        for (final InnerClassesTableEntry entry : innerClassesTable) {
            out.writeShort(entry.innerClassInfoIndex);
            out.writeShort(entry.outerClassInfoIndex);
            out.writeShort(entry.innerNameIndex);
            out.writeShort(entry.innerClassAccessFlags);
        }
    }

    public record InnerClassesTableEntry(int innerClassInfoIndex, int outerClassInfoIndex, int innerNameIndex,
            int innerClassAccessFlags) {

    }
}
