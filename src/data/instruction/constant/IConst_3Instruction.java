package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x06, mnemonic = "iconst_3")
public final class IConst_3Instruction extends IConstInstruction {

    public IConst_3Instruction() {

    }

    public static IConst_3Instruction read(DataInputStream in) throws IOException {
        return new IConst_3Instruction();
    }

    @Override
    public Integer getValue() {
        return 3;
    }
}
