package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA1, mnemonic = "if_icmplt")
public final class If_icmpltInstruction extends IfCompareInstruction<Integer> {

    protected If_icmpltInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpltInstruction read(DataInputStream in) throws IOException {
        return new If_icmpltInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Integer a, Integer b) {
        return a < b;
    }

}
