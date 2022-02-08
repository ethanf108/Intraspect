package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA5, mnemonic = "if_acmpeq")
public final class If_acmpeqInstruction extends IfCompareInstruction<Object> {

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
