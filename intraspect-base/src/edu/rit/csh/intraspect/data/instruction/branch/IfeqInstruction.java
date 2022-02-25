package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x99, mnemonic = "ifeq")
public final class IfeqInstruction extends IfInstruction {

    @AssembleInject
    public IfeqInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static IfeqInstruction read(final DataInputStream in) throws IOException {
        return new IfeqInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final int value) {
        return value == 0;
    }
}
