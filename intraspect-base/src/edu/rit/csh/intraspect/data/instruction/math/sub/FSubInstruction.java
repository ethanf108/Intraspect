package edu.rit.csh.intraspect.data.instruction.math.sub;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x66, mnemonic = "fsub")
public final class FSubInstruction extends SubInstruction<Float> {

    @AssembleInject
    public FSubInstruction() {

    }

    public static FSubInstruction read(final DataInputStream in) {
        return new FSubInstruction();
    }
}
