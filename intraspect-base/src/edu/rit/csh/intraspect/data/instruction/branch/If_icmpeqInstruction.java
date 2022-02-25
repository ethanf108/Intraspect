package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9F, mnemonic = "if_icmpeq")
public final class If_icmpeqInstruction extends IfCompareInstruction<Integer> {

    @AssembleInject
    public If_icmpeqInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpeqInstruction read(final DataInputStream in) throws IOException {
        return new If_icmpeqInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Integer a, final Integer b) {
        return a.equals(b);
    }
}
