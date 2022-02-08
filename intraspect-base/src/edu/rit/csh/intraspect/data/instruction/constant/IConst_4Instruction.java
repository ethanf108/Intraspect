package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x07, mnemonic = "iconst_4")
public final class IConst_4Instruction extends IConstInstruction {

    public IConst_4Instruction() {

    }

    public static IConst_4Instruction read(final DataInputStream in) throws IOException {
        return new IConst_4Instruction();
    }

    @Override
    public Integer getValue() {
        return 4;
    }
}
