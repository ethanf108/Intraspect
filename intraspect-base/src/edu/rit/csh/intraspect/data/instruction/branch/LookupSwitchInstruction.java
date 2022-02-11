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

@Opcode(opcode = 0xAB, mnemonic = "lookupswitch")
public final class LookupSwitchInstruction extends Instruction {

    private final int defaultOffset;
    private final MatchOffsetPair[] matchOffsetPairs;
    private transient int padBytes;

    public LookupSwitchInstruction(final int defaultOffset, final MatchOffsetPair[] matchOffsetPairs) {
        this.defaultOffset = defaultOffset;
        this.matchOffsetPairs = matchOffsetPairs;
    }

    public static LookupSwitchInstruction read(final DataInputStream in_) throws IOException {
        final OffsetInputStream in = (OffsetInputStream) in_;
        final int padBytes = (int) (4 - (in.getCounter() % 4));
        for (int i = 0; i < padBytes; i++) {
            in.readByte();
        }

        final int defaultOffset = in.readInt();

        final int numPairs = in.readInt();

        MatchOffsetPair[] mops = new MatchOffsetPair[numPairs];

        for (int i = 0; i < numPairs; i++) {
            mops[i] = MatchOffsetPair.read(in);
        }

        final LookupSwitchInstruction ret = new LookupSwitchInstruction(defaultOffset, mops);
        ret.padBytes = padBytes;
        return ret;
    }


    @Override
    public int getNumOperands() {
        return 8 + this.padBytes + 8 * this.matchOffsetPairs.length;
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
        return true;
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
