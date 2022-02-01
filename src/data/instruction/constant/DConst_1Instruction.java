package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0F, mnemonic = "dconst_0")
public final class DConst_1Instruction extends DConstInstruction {

    public DConst_1Instruction() {
    }

    public static DConst_1Instruction read(DataInputStream in) throws IOException {
        return new DConst_1Instruction();
    }

    @Override
    public Double getValue() {
        return 1d;
    }
}
