package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA6, mnemonic = "if_acmpne")
public final class If_acmpneInstruction extends IfCompareInstruction<Object> {

    protected If_acmpneInstruction(int branchTarget) {
        super(branchTarget);
    }

    public static If_acmpneInstruction read(DataInputStream in) throws IOException {
        return new If_acmpneInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(Object a, Object b) {
        return a != b;
    }

}
