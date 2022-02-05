package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9C, mnemonic = "ifge")
public final class IfgeInstruction extends IfInstruction {

    public IfgeInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfgeInstruction read(DataInputStream in) throws IOException {
        return new IfgeInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(int value) {
        return value >= 0;
    }

}