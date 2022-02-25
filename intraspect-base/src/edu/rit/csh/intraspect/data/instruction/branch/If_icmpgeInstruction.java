package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA2, mnemonic = "if_icmpge")
public final class If_icmpgeInstruction extends IfCompareInstruction<Integer> {

    @AssembleInject
    public If_icmpgeInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpgeInstruction read(final DataInputStream in) throws IOException {
        return new If_icmpgeInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Integer a, final Integer b) {
        return a >= b;
    }

}
