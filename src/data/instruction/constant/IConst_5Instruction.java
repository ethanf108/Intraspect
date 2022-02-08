package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x08, mnemonic = "iconst_5")
public final class IConst_5Instruction extends IConstInstruction {

    public IConst_5Instruction() {

    }

    public static IConst_5Instruction read(final DataInputStream in) throws IOException {
        return new IConst_5Instruction();
    }

    @Override
    public Integer getValue() {
        return 5;
    }
}
