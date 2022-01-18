package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

@AttributeName("BootstrapMethods")
public class BootstrapMethodsAttribute implements AttributeDesc {

    private final int attributeNameIndex;
    private final BootstrapMethodsTableEntry[] bootstrapMethodsTable;

    private BootstrapMethodsAttribute(final int attributeNameIndex, final BootstrapMethodsTableEntry[] bootstrapMethodsTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.bootstrapMethodsTable = bootstrapMethodsTable;
    }

    public static BootstrapMethodsAttribute read(final int ani, final DataInputStream in) throws IOException {
        in.readInt();    // Discard attribute length

        final BootstrapMethodsTableEntry[] arr = new BootstrapMethodsTableEntry[in.readUnsignedShort()];
        for (int i = 0; i < arr.length; i++) {
            final int bootstrapMethodRef = in.readUnsignedShort();
            final int numBoostrapArguments = in.readUnsignedShort();

            final int[] bootstrapArguments = new int[numBoostrapArguments];
            for (int j = 0; j < bootstrapArguments.length; bootstrapArguments[j++] = in.readUnsignedShort());

            arr[i] = new BootstrapMethodsTableEntry(bootstrapMethodRef, numBoostrapArguments, bootstrapArguments);
        }

        return new BootstrapMethodsAttribute(ani, arr);
    }

    @Override
    public void write(DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(getBootstrapMethodsTableLength());

        for (final BootstrapMethodsTableEntry entry : bootstrapMethodsTable) {
            out.writeShort(entry.bootstrapMethodRef);
            out.writeShort(entry.numBootstrapArguments);
            for (int bootstrapArgument : entry.bootstrapArguments) {
                out.writeShort(bootstrapArgument);
            }
        }
    }

    @Override
    public int getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public int getBootstrapMethodsTableLength() {
        return this.bootstrapMethodsTable.length;
    }

    @Override
    public int getDataLength() {
        int entryLengthSum = 2;
        for (final BootstrapMethodsTableEntry entry : bootstrapMethodsTable) {
            entryLengthSum += 4 + 2 * entry.numBootstrapArguments;
        }
        return entryLengthSum;
    }

    public BootstrapMethodsTableEntry[] getBootstrapMethodsTable() {
        return this.bootstrapMethodsTable;
    }

    public static record BootstrapMethodsTableEntry(int bootstrapMethodRef, int numBootstrapArguments,
            int[] bootstrapArguments) {

    }
}
