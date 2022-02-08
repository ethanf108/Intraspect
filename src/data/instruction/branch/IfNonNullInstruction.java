package data.instruction.branch;

import data.instruction.IfObjectInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC7, mnemonic = "ifnonnull")
public final class IfNonNullInstruction extends IfObjectInstruction {

    protected IfNonNullInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static IfNonNullInstruction read(final DataInputStream in) throws IOException {
        return new IfNonNullInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Object value) {
        return value != null;
    }

}
