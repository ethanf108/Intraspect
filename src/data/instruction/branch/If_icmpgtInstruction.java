package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA3, mnemonic = "if_icmpgt")
public final class If_icmpgtInstruction extends IfCompareInstruction<Integer> {

    protected If_icmpgtInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static If_icmpgtInstruction read(DataInputStream in) throws IOException {
        return new If_icmpgtInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Integer a, Integer b) {
        return a > b;
    }

}
