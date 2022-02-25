package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA5, mnemonic = "if_acmpeq")
public final class If_acmpeqInstruction extends IfCompareInstruction<Object> {

    @AssembleInject
    public If_acmpeqInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_acmpeqInstruction read(final DataInputStream in) throws IOException {
        return new If_acmpeqInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Object a, final Object b) {
        return a == b;
    }

}
