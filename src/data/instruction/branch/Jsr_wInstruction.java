package data.instruction.branch;

import data.instruction.BranchWideInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC9, mnemonic = "jsr_W")
public final class Jsr_wInstruction extends BranchWideInstruction {

    public Jsr_wInstruction(final long branchIndex) {
        super(branchIndex);
    }

    public static Jsr_wInstruction read(final DataInputStream in) throws IOException {
        return new Jsr_wInstruction(Integer.toUnsignedLong(in.readInt()));
    }
}
