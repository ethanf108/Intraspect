package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.util.OffsetInputStream;

import java.io.DataInputStream;
import java.io.IOException;
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


    @Override
    public int[] getOperands() {
        int[] ret = new int[this.getNumOperands()];

        ret[0] = (this.high & 0xFF000000) >> 24;
        ret[1] = (this.high & 0xFF0000) >> 16;
        ret[2] = (this.high & 0xFF00) >> 8;
        ret[3] = this.high & 0xFF;

        ret[4] = (this.low & 0xFF000000) >> 24;
        ret[5] = (this.low & 0xFF0000) >> 16;
        ret[6] = (this.low & 0xFF00) >> 8;
        ret[7] = this.low & 0xFF;

        for (int i = 0, i4 = 0; i < this.jumpOffsets.length; i++, i4 = i << 2) {
            ret[8 + i4] = (this.jumpOffsets[i] & 0xFF000000) >> 24;
            ret[9 + i4] = (this.jumpOffsets[i] & 0xFF0000) >> 16;
            ret[10 + i4] = (this.jumpOffsets[i] & 0xFF00) >> 8;
            ret[11 + i4] = this.jumpOffsets[i] & 0xFF;
        }

        int[] actualRet = new int[ret.length + this.padBytes];
        System.arraycopy(ret, 0, actualRet, this.padBytes, ret.length);
        return actualRet;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO implement
        return true;
    }
}
