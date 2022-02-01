package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0A, mnemonic = "lconst_1")
public final class LConst_1Instruction extends LConstInstruction {

    public LConst_1Instruction() {
    }

    public static LConst_1Instruction read(DataInputStream in) throws IOException {
        return new LConst_1Instruction();
    }

    @Override
    public Long getValue() {
        return 1L;
    }
}
