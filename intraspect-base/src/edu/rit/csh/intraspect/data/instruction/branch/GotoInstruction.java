package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA7, mnemonic = "goto")
public final class GotoInstruction extends BranchInstruction {

    public GotoInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static GotoInstruction read(final DataInputStream in) throws IOException {
        return new GotoInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return false;
    }
}
