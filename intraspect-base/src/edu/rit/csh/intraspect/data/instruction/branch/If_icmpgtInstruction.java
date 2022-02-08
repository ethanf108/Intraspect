package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA3, mnemonic = "if_icmpgt")
public final class If_icmpgtInstruction extends IfCompareInstruction<Integer> {

    public If_icmpgtInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpgtInstruction read(final DataInputStream in) throws IOException {
        return new If_icmpgtInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Integer a, final Integer b) {
        return a > b;
    }

}
