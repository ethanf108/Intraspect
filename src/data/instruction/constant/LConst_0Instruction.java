package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x09, mnemonic = "lconst_0")
public final class LConst_0Instruction extends LConstInstruction {

    public LConst_0Instruction() {

    }

    public static LConst_1Instruction read(DataInputStream in) throws IOException {
        return new LConst_1Instruction();
    }

    @Override
    public Long getValue() {
        return 0L;
    }
}
