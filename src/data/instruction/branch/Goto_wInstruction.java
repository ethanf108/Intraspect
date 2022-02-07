package data.instruction.branch;

import data.instruction.BranchWideInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC8, mnemonic = "goto_w")
public final class Goto_wInstruction extends BranchWideInstruction {

    public Goto_wInstruction(long branchIndex) {
        super(branchIndex);
    }

    public static Goto_wInstruction read(DataInputStream in) throws IOException {
        return new Goto_wInstruction(Integer.toUnsignedLong(in.readInt()));
    }
}
