package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x05, mnemonic = "iconst_2")
public final class IConst_2Instruction extends IConstInstruction {

    public IConst_2Instruction() {

    }

    public static IConst_2Instruction read(DataInputStream in) throws IOException {
        return new IConst_2Instruction();
    }

    @Override
    public Integer getValue() {
        return 2;
    }
}
