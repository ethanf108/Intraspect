package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0E, mnemonic = "dconst_0")
public final class DConst_0Instruction extends DConstInstruction {

    public DConst_0Instruction() {

    }

    public static DConst_0Instruction read(final DataInputStream in) throws IOException {
        return new DConst_0Instruction();
    }

    @Override
    public Double getValue() {
        return 0d;
    }
}
