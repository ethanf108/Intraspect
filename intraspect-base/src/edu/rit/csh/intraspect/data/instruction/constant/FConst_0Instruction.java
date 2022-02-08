package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0B, mnemonic = "fconst_0")
public final class FConst_0Instruction extends FConstInstruction {

    public FConst_0Instruction() {

    }

    public static FConst_0Instruction read(final DataInputStream in) throws IOException {
        return new FConst_0Instruction();
    }

    @Override
    public Float getValue() {
        return 0f;
    }
}
