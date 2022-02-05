package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9B, mnemonic = "iflt")
public final class IfltInstruction extends IfInstruction {

    public IfltInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfltInstruction read(DataInputStream in) throws IOException {
        return new IfltInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(int value) {
        return value < 0;
    }

}
