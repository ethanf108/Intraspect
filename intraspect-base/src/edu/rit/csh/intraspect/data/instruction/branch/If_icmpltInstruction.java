package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA1, mnemonic = "if_icmplt")
public final class If_icmpltInstruction extends IfCompareInstruction<Integer> {

    public If_icmpltInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpltInstruction read(final DataInputStream in) throws IOException {
        return new If_icmpltInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Integer a, final Integer b) {
        return a < b;
    }

}
