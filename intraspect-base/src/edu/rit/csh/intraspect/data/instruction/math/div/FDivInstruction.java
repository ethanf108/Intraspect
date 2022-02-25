package edu.rit.csh.intraspect.data.instruction.math.div;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x6e, mnemonic = "fdiv")
public final class FDivInstruction extends DivInstruction<Float> {

    @AssembleInject
    public FDivInstruction() {

    }

    public static FDivInstruction read(final DataInputStream in) {
        return new FDivInstruction();
    }
}
