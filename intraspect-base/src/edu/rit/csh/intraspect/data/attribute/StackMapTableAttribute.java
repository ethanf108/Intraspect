package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.attribute.stackmaptable.StackMapFrame;
import edu.rit.csh.intraspect.data.constant.UTF8Constant;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The StackMapTable attribute.
 */
@AttributeName("StackMapTable")
public final class StackMapTableAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
    private final int attributeNameIndex;

    private final StackMapFrame[] entries;

    public StackMapTableAttribute(final int attributeNameIndex, final StackMapFrame[] entries) {
        this.attributeNameIndex = attributeNameIndex;
        this.entries = entries;
    }

    public static StackMapTableAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Ignore

        final int numberOfEntries = in.readUnsignedShort();

        final StackMapFrame[] entries = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            entries[i] = StackMapFrame.read(in);
        }

        return new StackMapTableAttribute(ani, entries);
    }

    public StackMapFrame[] getEntries() {
        return this.entries;
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    @Override
    public int getDataLength() {
        int dataLength = 2;
        for (final StackMapFrame entry : this.entries) {
            dataLength += entry.getDataLength();
        }
        return dataLength;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.entries.length);
        for (final StackMapFrame entry : this.entries) {
            entry.write(out);
        }
    }
}
