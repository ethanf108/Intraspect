package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA0, mnemonic = "if_icmpne")
public final class If_icmpneInstruction extends IfCompareInstruction<Integer> {

    @AssembleInject
    public If_icmpneInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpneInstruction read(final DataInputStream in) throws IOException {
        return new If_icmpneInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Integer a, final Integer b) {
        return !a.equals(b);
    }

}
