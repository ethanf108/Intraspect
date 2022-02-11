package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.util.OffsetInputStream;
import edu.rit.csh.intraspect.util.OffsetOutputStream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

@Opcode(opcode = 0xAA, mnemonic = "tableswitch")
public final class TableSwitchInstruction extends Instruction {

    private final int defaultOffset;
    private final int high;
    private final int low;

    private final int[] jumpOffsets;

    private transient int padBytes = -1;

    public TableSwitchInstruction(final int defaultOffset, final int high, final int low, final int[] jumpOffsets) {
        this.defaultOffset = defaultOffset;
        this.high = high;
        this.low = low;
        this.jumpOffsets = jumpOffsets;
    }

    public static TableSwitchInstruction read(final DataInputStream in_) throws IOException {
        final OffsetInputStream in = (OffsetInputStream) in_;
        final int padBytes = (int) (4 - (in.getCounter() % 4));
        for (int i = 0; i < padBytes; i++) {
            in.readByte();
        }

        final int defaultOffset = in.readInt();
        final int low = in.readInt();
        final int high = in.readInt();

        int[] jumps = new int[high - low + 1];
        for (int i = 0; i < high - low + 1; i++) {
            jumps[i] = in.readInt();
        }

        final TableSwitchInstruction ret = new TableSwitchInstruction(defaultOffset, high, low, jumps);
        ret.padBytes = padBytes;
        return ret;
    }

    public int getPadBytes() {
        return this.padBytes;
    }

    public int getDefaultOffset() {
        return this.defaultOffset;
    }

    public int getHigh() {
        return this.high;
    }

    public int getLow() {
        return this.low;
    }

    public int[] getJumpOffsets() {
        return Arrays.copyOf(this.jumpOffsets, this.jumpOffsets.length);
    }

    @Override
    public boolean isPadded() {
        return true;
    }

    @Override
    public int getNumOperands() {
        return 12 + 4 * this.jumpOffsets.length + this.padBytes;
    }

    /**
     * Warning: please call write with a proper OffsetOutputStream first.
     * We have no way of calculating the padBytes without this.
     *
     * @return an int array representing the unsigned bytes of the operands of this method
     */
    @Override
    public int[] getOperands() {
        ByteBuffer buf = ByteBuffer.allocate(this.getNumOperands());

        for (int i = 0; i < this.padBytes; i++) {
            buf.put((byte) 0);
        }

        buf.putInt(this.defaultOffset);
        buf.putInt(this.low);
        buf.putInt(this.high);
        for (int jump : this.jumpOffsets) {
            buf.putInt(jump);
        }

        byte[] b = buf.array();
        int[] ret = new int[b.length];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = b[i];
        }
        return ret;

    }

    @Override
    public void write(DataOutputStream stream) throws IOException {
        final OffsetOutputStream out = (OffsetOutputStream) stream;
        out.writeByte(this.getOpcode());
        this.padBytes = (int) (3 - (out.getCounter() % 4));
        int[] ops = this.getOperands();
        for (int i : ops) {
            out.writeByte(i);
        }
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO implement
        return true;
    }
}
