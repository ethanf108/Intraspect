package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x04, mnemonic = "iconst_1")
public final class IConst_1Instruction extends IConstInstruction {

    public IConst_1Instruction() {

    }

    public static IConst_1Instruction read(final DataInputStream in) throws IOException {
        return new IConst_1Instruction();
    }

    @Override
    public Integer getValue() {
        return 1;
    }
}
