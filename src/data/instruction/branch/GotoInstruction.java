package data.instruction.branch;

import data.ClassFile;
import data.instruction.BranchInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA7, mnemonic = "goto")
public final class GotoInstruction extends BranchInstruction {

    public GotoInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static GotoInstruction read(DataInputStream in) throws IOException {
        return new GotoInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return false;
    }
}
