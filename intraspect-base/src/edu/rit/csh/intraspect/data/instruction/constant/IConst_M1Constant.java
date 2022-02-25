package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x02, mnemonic = "iconst_m1")
public final class IConst_M1Constant extends IConstInstruction {

    @AssembleInject
    public IConst_M1Constant() {

    }

    public static IConst_M1Constant read(final DataInputStream in) throws IOException {
        return new IConst_M1Constant();
    }

    @Override
    public Integer getValue() {
        return -1;
    }
}
