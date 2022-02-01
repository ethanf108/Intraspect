package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0B, mnemonic = "fconst_0")
public final class FConst_0Instruction extends FConstInstruction {

    public FConst_0Instruction() {

    }

    public static FConst_0Instruction read(DataInputStream in) throws IOException {
        return new FConst_0Instruction();
    }

    @Override
    public Float getValue() {
        return 0f;
    }
}
