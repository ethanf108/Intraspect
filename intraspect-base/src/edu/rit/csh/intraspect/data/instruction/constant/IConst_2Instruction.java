package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x05, mnemonic = "iconst_2")
public final class IConst_2Instruction extends IConstInstruction {

    @AssembleInject
    public IConst_2Instruction() {

    }

    public static IConst_2Instruction read(final DataInputStream in) throws IOException {
        return new IConst_2Instruction();
    }

    @Override
    public Integer getValue() {
        return 2;
    }
}
