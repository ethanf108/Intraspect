package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA4, mnemonic = "if_icmple")
public final class If_icmpleInstruction extends IfCompareInstruction<Integer> {

    @AssembleInject
    public If_icmpleInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpleInstruction read(final DataInputStream in) throws IOException {
        return new If_icmpleInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Integer a, final Integer b) {
        return a <= b;
    }

}
