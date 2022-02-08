package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC7, mnemonic = "ifnonnull")
public final class IfNonNullInstruction extends IfObjectInstruction {

    public IfNonNullInstruction(final int branchTarget) {
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
