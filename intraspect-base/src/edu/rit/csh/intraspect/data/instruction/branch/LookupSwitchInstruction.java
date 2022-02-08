package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Opcode(opcode = 0xAB, mnemonic = "lookupswitch")
public final class LookupSwitchInstruction extends Instruction {

    private final transient int padBytes;

    private final int defaultOffset;

    private final MatchOffsetPair[] matchOffsetPairs;

    public LookupSwitchInstruction(final int padBytes, final int defaultOffset, final MatchOffsetPair[] matchOffsetPairs) {
        this.padBytes = padBytes;
        this.defaultOffset = defaultOffset;
        this.matchOffsetPairs = matchOffsetPairs;
    }

    public static LookupSwitchInstruction read(final DataInputStream in, final int padBytes) throws IOException {
        for (int i = 0; i < padBytes; i++) {
            in.readByte();
        }

        final int defaultOffset = in.readInt();

        final int numPairs = in.readInt();

        MatchOffsetPair[] mops = new MatchOffsetPair[numPairs];

        for (int i = 0; i < numPairs; i++) {
            mops[i] = MatchOffsetPair.read(in);
        }

        return new LookupSwitchInstruction(padBytes, defaultOffset, mops);
    }


    @Override
    public int getNumOperands() {
        return 8 + this.padBytes + (this.matchOffsetPairs.length << 3);
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
    public boolean isValid(final ClassFile ref) {
        return false;
    }

    @Override
    public boolean isPadded() {
        return true;
    }

    public record MatchOffsetPair(int match, int offset) {

        public static MatchOffsetPair read(DataInputStream in) throws IOException {
            return new MatchOffsetPair(in.readInt(), in.readInt());
        }
    }
}
