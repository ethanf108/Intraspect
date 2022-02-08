package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x09, mnemonic = "lconst_0")
public final class LConst_0Instruction extends LConstInstruction {

    public LConst_0Instruction() {

    }

    public static LConst_0Instruction read(final DataInputStream in) throws IOException {
        return new LConst_0Instruction();
    }

    @Override
    public Long getValue() {
        return 0L;
    }
}
