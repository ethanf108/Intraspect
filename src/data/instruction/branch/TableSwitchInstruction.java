package data.instruction.branch;

import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAA, mnemonic = "tableswitch")
public final class TableSwitchInstruction extends Instruction {

    private final int defaultOffset;
    private final int high;
    private final int low;

    private final int[] jumpOffsets;

    private final transient int padBytes;

    public TableSwitchInstruction(final int padBytes, final int defaultOffset, final int high, final int low, final int[] jumpOffsets) {
        this.defaultOffset = defaultOffset;
        this.high = high;
        this.low = low;
        this.jumpOffsets = jumpOffsets;
        this.padBytes = padBytes;
    }

    public static TableSwitchInstruction read(final DataInputStream in, final int padBytes) throws IOException {
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

        return new TableSwitchInstruction(padBytes, defaultOffset, high, low, jumps);
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
        int[] ret = new int[this.jumpOffsets.length];
        System.arraycopy(this.jumpOffsets, 0, ret, 0, this.jumpOffsets.length);
        return ret;
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

        for (int i = 0; i < this.jumpOffsets.length; i++) {
            ret[8 + 4 * i] = (this.jumpOffsets[i] & 0xFF000000) >> 24;
            ret[8 + 4 * i] = (this.jumpOffsets[i] & 0xFF0000) >> 16;
            ret[8 + 4 * i] = (this.jumpOffsets[i] & 0xFF00) >> 8;
            ret[8 + 4 * i] = this.jumpOffsets[i] & 0xFF;
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
