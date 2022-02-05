package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9E, mnemonic = "ifle")
public final class IfleInstruction extends IfInstruction {

    public IfleInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfleInstruction read(DataInputStream in) throws IOException {
        return new IfleInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(int value) {
        return value <= 0;
    }

}
