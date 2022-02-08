package data.instruction.branch;

import data.instruction.IfInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9A, mnemonic = "ifne")
public final class IfneInstruction extends IfInstruction {

    public IfneInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfneInstruction read(final DataInputStream in) throws IOException {
        return new IfneInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final int value) {
        return value != 0;
    }

}
