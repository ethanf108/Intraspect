package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x0A, mnemonic = "lconst_1")
public final class LConst_1Instruction extends LConstInstruction {

    public LConst_1Instruction() {
    }

    public static LConst_1Instruction read(final DataInputStream in) throws IOException {
        return new LConst_1Instruction();
    }

    @Override
    public Long getValue() {
        return 1L;
    }
}
