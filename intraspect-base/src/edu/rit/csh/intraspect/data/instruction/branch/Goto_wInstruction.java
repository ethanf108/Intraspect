package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC8, mnemonic = "goto_w")
public final class Goto_wInstruction extends BranchWideInstruction {

    @AssembleInject
    public Goto_wInstruction(final long branchIndex) {
        super(branchIndex);
    }

    public static Goto_wInstruction read(final DataInputStream in) throws IOException {
        return new Goto_wInstruction(Integer.toUnsignedLong(in.readInt()));
    }
}
