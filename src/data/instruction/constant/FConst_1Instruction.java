package data.instruction.constant;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0C, mnemonic = "fconst_1")
public final class FConst_1Instruction extends FConstInstruction {

    public FConst_1Instruction() {

    }

    public FConst_1Instruction read(final DataInputStream in) throws IOException {
        return new FConst_1Instruction();
    }

    @Override
    public Float getValue() {
        return 1f;
    }
}
