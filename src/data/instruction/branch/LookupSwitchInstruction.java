package data.instruction.branch;

import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Opcode(opcode = 0xAB, mnemonic = "lookupswitch")
public final class LookupSwitchInstruction extends Instruction {

    private final transient int padBytes;

    private final int defaultOffset;

    private final MatchOffsetPair[] matchOffsetPairs;

    public LookupSwitchInstruction(int padBytes, int defaultOffset, MatchOffsetPair[] matchOffsetPairs) {
        this.padBytes = padBytes;
        this.defaultOffset = defaultOffset;
        this.matchOffsetPairs = matchOffsetPairs;
    }

    public static LookupSwitchInstruction read(DataInputStream in, int padBytes) throws IOException {
        System.out.println("PAD: " + padBytes);
        for (int i = 0; i < padBytes; i++) {
            in.readByte();
        }

        final int defaultOffset = in.readInt();

        final int numPairs = in.readInt();

        System.out.println(defaultOffset + " " + numPairs);
        MatchOffsetPair[] mops = new MatchOffsetPair[numPairs];

        for (int i = 0; i < numPairs; i++) {
            mops[i] = MatchOffsetPair.read(in);
        }

        return new LookupSwitchInstruction(padBytes, defaultOffset, mops);
    }


    @Override
    public int getNumOperands() {
        return 8 + this.padBytes + 8 * this.matchOffsetPairs.length;
    }

    @Override
    public int[] getOperands() {
        ByteBuffer buf = ByteBuffer.allocate(this.getNumOperands());

        for (int i = 0; i < this.padBytes; i++) {
            buf.put((byte) 0);
        }

        buf.putInt(this.defaultOffset);
        buf.putInt(this.matchOffsetPairs.length);

        for (MatchOffsetPair mop : this.matchOffsetPairs) {
            buf.putInt(mop.match);
            buf.putInt(mop.offset);
        }

        final byte[] bytes = buf.array();
        int[] ret = new int[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            ret[i] = bytes[i] & 0xFF;
        }

        return ret;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return false;
    }

    public record MatchOffsetPair(int match, int offset) {

        public static MatchOffsetPair read(DataInputStream in) throws IOException {
            return new MatchOffsetPair(in.readInt(), in.readInt());
        }
    }
}
