package edu.rit.csh.intraspect.data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class LocalVarTarget extends TargetInfo {

    private TableEntry[] table;

    public LocalVarTarget(final int targetType) {
        super(targetType);
    }

    public TableEntry[] getTable() {
        return this.table;
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {

        out.writeByte(this.targetType);

        out.writeShort(this.table.length);

        for (final TableEntry tableEntry : this.table) {
            tableEntry.write(out);
        }

    }

    @Override
    public int getDataLength() {
        return this.table.length * 6 + 3;
    }

    @Override
    protected TargetInfo readInternal(final DataInputStream in) throws IOException {

        final int tableLength = in.readUnsignedShort();

        this.table = new TableEntry[tableLength];

        for (int i = 0; i < this.table.length; i++) {
            this.table[i] = TableEntry.read(in);
        }

        return this;
    }

    public record TableEntry(int startPc, int length, int index) {

        public static TableEntry read(final DataInputStream in) throws IOException {
            return new TableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.startPc);
            out.writeShort(this.length);
            out.writeShort(this.index);
        }
    }
}
