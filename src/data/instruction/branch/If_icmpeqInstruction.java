package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x9F, mnemonic = "if_icmpeq")
public final class If_icmpeqInstruction extends IfCompareInstruction<Integer> {

    protected If_icmpeqInstruction(final int branchTarget) {
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
