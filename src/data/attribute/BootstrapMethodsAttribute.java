package data.attribute;

import data.AttributeDesc;
import data.AttributeName;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static util.Util.*;

@AttributeName("BootstrapMethods")
public class BootstrapMethodsAttribute implements AttributeDesc {

    private final short attributeNameIndex;
    private final BootstrapMethodsTableEntry[] bootstrapMethodsTable;

    private BootstrapMethodsAttribute(final short attributeNameIndex, final BootstrapMethodsTableEntry[] bootstrapMethodsTable) {
        this.attributeNameIndex = attributeNameIndex;
        this.bootstrapMethodsTable = bootstrapMethodsTable;
    }

    public static BootstrapMethodsAttribute read(final short ani, final InputStream in) throws IOException {
        readInt(in);    // Discard attribute length

        final BootstrapMethodsTableEntry[] arr = new BootstrapMethodsTableEntry[readShort(in)];
        for (int i = 0; i < arr.length; i++) {
            final short bootstrapMethodRef = readShort(in);
            final short numBoostrapArguments = readShort(in);

            final short[] bootstrapArguments = new short[numBoostrapArguments];
            for (int j = 0; j < bootstrapArguments.length; bootstrapArguments[j++] = readShort(in));

            arr[i] = new BootstrapMethodsTableEntry(bootstrapMethodRef, numBoostrapArguments, bootstrapArguments);
        }

        return new BootstrapMethodsAttribute(ani, arr);
    }

    @Override
    public void write(OutputStream out) throws IOException {
        writeShort(out, this.attributeNameIndex);
        writeInt(out, this.getDataLength());
        writeShort(out, getBootstrapMethodsTableLength());

        for (final BootstrapMethodsTableEntry entry : bootstrapMethodsTable) {
            writeShort(out, entry.bootstrapMethodRef);
            writeShort(out, entry.numBootstrapArguments);
            for (short bootstrapArgument : entry.bootstrapArguments) {
                writeShort(out, bootstrapArgument);
            }
        }
    }

    @Override
    public short getAttributeNameIndex() {
        return this.attributeNameIndex;
    }

    public short getBootstrapMethodsTableLength() {
        return (short) this.bootstrapMethodsTable.length;
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

    private static record BootstrapMethodsTableEntry(short bootstrapMethodRef, short numBootstrapArguments,
            short[] bootstrapArguments) {

    }
}
