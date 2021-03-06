package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.ConstantPoolIndexedRecord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The InnerClasses attribute.
 */
@AttributeName("InnerClasses")
public final class InnerClassesAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    private final InnerClassesTableEntry[] innerClassesTable;

    private InnerClassesAttribute(final int attributeNameIndex, final InnerClassesTableEntry[] innerClassesTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.innerClassesTable = innerClassesTable;
    }

    public static InnerClassesAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final InnerClassesTableEntry[] arr = new InnerClassesTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; arr[i++] = InnerClassesTableEntry.read(in)) {
        }

        return new InnerClassesAttribute(ani, arr);
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        return 2 + this.getInnerClassesTableLength() * 8;
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
        out.writeShort(this.getInnerClassesTableLength());

        for (final InnerClassesTableEntry entry : this.innerClassesTable) {
            entry.write(out);
        }
    }

    public record InnerClassesTableEntry(
            @ConstantPoolIndex(ClassConstant.class) int innerClassInfoIndex,
            @ConstantPoolIndex(value = ClassConstant.class, nullable = true) int outerClassInfoIndex,
            @ConstantPoolIndex(UTF8Constant.class) int innerNameIndex,
            int innerClassAccessFlags
    ) implements ConstantPoolIndexedRecord<InnerClassesTableEntry> {

        public static InnerClassesTableEntry read(final DataInputStream in) throws IOException {
            return new InnerClassesTableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.innerClassInfoIndex);
            out.writeShort(this.outerClassInfoIndex);
            out.writeShort(this.innerNameIndex);
            out.writeShort(this.innerClassAccessFlags);
        }

        @Override
        public InnerClassesTableEntry shift(int index, int delta) {
            return new InnerClassesTableEntry(
                    this.innerClassInfoIndex >= index ? this.innerClassInfoIndex + delta : this.innerClassInfoIndex,
                    this.outerClassInfoIndex >= index ? this.outerClassInfoIndex + delta : this.outerClassInfoIndex,
                    this.innerNameIndex >= index ? this.innerNameIndex + delta : this.innerNameIndex,
                    this.innerClassAccessFlags
            );
        }
    }
}
