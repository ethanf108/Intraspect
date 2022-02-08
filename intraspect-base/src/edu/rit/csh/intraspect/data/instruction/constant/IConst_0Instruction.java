package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x03, mnemonic = "iconst_0")
public final class IConst_0Instruction extends IConstInstruction {

    public IConst_0Instruction() {

    }

    public static IConst_0Instruction read(final DataInputStream in) throws IOException {
        return new IConst_0Instruction();
    }

    @Override
    public Integer getValue() {
        return 0;
    }
}
