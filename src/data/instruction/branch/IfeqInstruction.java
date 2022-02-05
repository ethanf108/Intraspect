package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x99, mnemonic = "ifeq")
public final class IfeqInstruction extends IfInstruction {

    public IfeqInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfeqInstruction read(DataInputStream in) throws IOException {
        return new IfeqInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(int value) {
        return value == 0;
    }
}
