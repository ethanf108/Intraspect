package edu.rit.csh.intraspect.data.instruction.math.rem;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x73, mnemonic = "drem")
public final class DRemInstruction extends RemInstruction<Double> {

    @AssembleInject
    public DRemInstruction() {

    }

    public static DRemInstruction read(final DataInputStream in) {
        return new DRemInstruction();
    }
}
