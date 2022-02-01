package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x07, mnemonic = "iconst_4")
public final class IConst_4Instruction extends IConstInstruction {

    public IConst_4Instruction() {

    }

    public static IConst_4Instruction read(DataInputStream in) throws IOException {
        return new IConst_4Instruction();
    }

    @Override
    public Integer getValue() {
        return 4;
    }
}
