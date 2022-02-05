package data.instruction.branch;

import data.instruction.IfObjectInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC7, mnemonic = "ifnonnull")
public final class IfNonNullInstruction extends IfObjectInstruction {

    protected IfNonNullInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfNonNullInstruction read(DataInputStream in) throws IOException {
        return new IfNonNullInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Object value) {
        return value != null;
    }

}
