package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0D, mnemonic = "fconst_2")
public final class FConst_2Instruction extends FConstInstruction {

    public FConst_2Instruction() {

    }

    public static FConst_2Instruction read(final DataInputStream in) throws IOException {
        return new FConst_2Instruction();
    }

    @Override
    public Float getValue() {
        return 2f;
    }
}
