package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA2, mnemonic = "if_icmpge")
public final class If_icmpgeInstruction extends IfCompareInstruction<Integer> {

    protected If_icmpgeInstruction(final int branchTarget) {
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
