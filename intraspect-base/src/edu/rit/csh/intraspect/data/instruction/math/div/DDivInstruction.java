package edu.rit.csh.intraspect.data.instruction.math.div;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x6f, mnemonic = "ddiv")
public final class DDivInstruction extends DivInstruction<Double> {

    @AssembleInject
    public DDivInstruction() {

    }

    public static DDivInstruction read(final DataInputStream in) {
        return new DDivInstruction();
    }
}
