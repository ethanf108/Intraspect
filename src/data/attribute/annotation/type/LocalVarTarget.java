package data.attribute.annotation.type;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class LocalVarTarget extends TargetInfo {

    public static record TableEntry(int startPc, int length, int index) {

        public static TableEntry read(final DataInputStream in) throws IOException {
            return new TableEntry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
        }

        public void write(final DataOutputStream out) throws IOException {
            out.writeShort(this.startPc);
            out.writeShort(this.length);
            out.writeShort(this.index);
        }
    }

    private TableEntry[] table;

    public TableEntry[] getTable() {
        return this.table;
    }

    public LocalVarTarget(final int targetType) {
        super(targetType);
    }

    @Override
    public void write(final DataOutputStream out) throws IOException {

        out.writeByte(this.targetType);

        out.writeShort(this.table.length);

        for (final TableEntry tableEntry : table) {
            tableEntry.write(out);
        }

    }

    @Override
    public int getDataLength() {
        return table.length * 6 + 3;
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
}
