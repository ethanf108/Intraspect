package data.instruction.branch;

import data.instruction.IfObjectInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC6, mnemonic = "ifnull")
public final class IfNullInstruction extends IfObjectInstruction {

    protected IfNullInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static IfNullInstruction read(DataInputStream in) throws IOException {
        return new IfNullInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Object value) {
        return value == null;
    }

}
