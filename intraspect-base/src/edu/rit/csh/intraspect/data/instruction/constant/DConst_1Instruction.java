package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0F, mnemonic = "dconst_1")
public final class DConst_1Instruction extends DConstInstruction {

    public DConst_1Instruction() {
    }

    public static DConst_1Instruction read(final DataInputStream in) throws IOException {
        return new DConst_1Instruction();
    }

    @Override
    public Double getValue() {
        return 1d;
    }
}
