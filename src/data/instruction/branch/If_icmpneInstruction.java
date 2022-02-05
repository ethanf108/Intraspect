package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA0, mnemonic = "if_icmpne")
public final class If_icmpneInstruction extends IfCompareInstruction<Integer> {

    protected If_icmpneInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpneInstruction read(DataInputStream in) throws IOException {
        return new If_icmpneInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Integer a, Integer b) {
        return !a.equals(b);
    }

}
