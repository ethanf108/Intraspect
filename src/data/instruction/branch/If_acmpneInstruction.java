package data.instruction.branch;

import data.instruction.IfCompareInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xA6, mnemonic = "if_acmpne")
public final class If_acmpneInstruction extends IfCompareInstruction<Object> {

    public If_acmpneInstruction(final int branchTarget) {
        super(branchTarget);
    }

    public static If_acmpneInstruction read(final DataInputStream in) throws IOException {
        return new If_acmpneInstruction(in.readUnsignedShort());
    }

    @Override
    public boolean test(final Object a, final Object b) {
        return a != b;
    }

}
