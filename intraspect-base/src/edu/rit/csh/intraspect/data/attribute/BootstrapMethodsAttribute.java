package edu.rit.csh.intraspect.data.attribute;

import edu.rit.csh.intraspect.data.constant.*;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.ConstantPoolIndexedRecord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * The BootstrapMethods attribute.
 */
@AttributeName("BootstrapMethods")
public final class BootstrapMethodsAttribute implements AttributeDesc {

    @ConstantPoolIndex(UTF8Constant.class)
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
            for (int j = 0; j < bootstrapArguments.length; bootstrapArguments[j++] = in.readUnsignedShort()) {
            }

            arr[i] = new BootstrapMethodsTableEntry(bootstrapMethodRef, numBoostrapArguments, bootstrapArguments);
        }

        return new BootstrapMethodsAttribute(ani, arr);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {
        out.writeShort(this.attributeNameIndex);
        out.writeInt(this.getDataLength());
        out.writeShort(this.getBootstrapMethodsTableLength());

        for (final BootstrapMethodsTableEntry entry : this.bootstrapMethodsTable) {
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
        for (final BootstrapMethodsTableEntry entry : this.bootstrapMethodsTable) {
            entryLengthSum += 4 + 2 * entry.numBootstrapArguments;
        }
        return entryLengthSum;
    }

    public BootstrapMethodsTableEntry[] getBootstrapMethodsTable() {
        return this.bootstrapMethodsTable;
    }

    public record BootstrapMethodsTableEntry(
            @ConstantPoolIndex(MethodHandleConstant.class) int bootstrapMethodRef,
            int numBootstrapArguments,
            @ConstantPoolIndex({
                    IntegerConstant.class,
                    FloatConstant.class,
                    LongConstant.class,
                    DoubleConstant.class,
                    ClassConstant.class,
                    StringConstant.class,
                    MethodHandleConstant.class,
                    MethodTypeConstant.class,
                    DynamicConstant.class
            }) int[] bootstrapArguments
    ) implements ConstantPoolIndexedRecord<BootstrapMethodsTableEntry> {

        @Override
        public BootstrapMethodsTableEntry shift(int index, int delta) {
            int[] na = new int[this.bootstrapArguments.length];
            for (int i = 0; i < na.length; i++) {
                na[i] = this.bootstrapArguments[i] >= index ? this.bootstrapArguments[i] + delta : this.bootstrapArguments[i];
            }
            return new BootstrapMethodsTableEntry(
                    this.bootstrapMethodRef >= index ? this.bootstrapMethodRef + delta : this.bootstrapMethodRef,
                    this.numBootstrapArguments,
                    na
            );
        }
    }
}
