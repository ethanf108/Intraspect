package edu.rit.csh.intraspect.data.instruction.math.sub;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x67, mnemonic = "dsub")
public final class DSubInstruction extends SubInstruction<Double> {

    @AssembleInject
    public DSubInstruction() {

    }

    public static DSubInstruction read(final DataInputStream in) {
        return new DSubInstruction();
    }
}
