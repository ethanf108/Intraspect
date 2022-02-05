package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA5, mnemonic = "if_acmpeq")
public final class If_acmpeqInstruction extends IfCompareInstruction<Object> {

    protected If_acmpeqInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static If_acmpeqInstruction read(DataInputStream in) throws IOException {
        return new If_acmpeqInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Object a, Object b) {
        return a == b;
    }

}
