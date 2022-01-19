package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import data.attribute.stackmaptable.StackMapFrame;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("StackMapTable")
public class StackMapTableAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final StackMapFrame[] entries;

    private StackMapTableAttribute(int attributeNameIndex, StackMapFrame[] entries) {
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
        for (final StackMapFrame entry : entries) {
            dataLength += entry.getDataLength();
        }
        return dataLength;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(getDataLength());
        out.writeShort(this.entries.length);
        for (StackMapFrame entry : entries) {
            entry.write(out);
        }
    }
}
