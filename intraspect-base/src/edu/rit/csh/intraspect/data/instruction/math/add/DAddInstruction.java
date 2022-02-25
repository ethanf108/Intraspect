package edu.rit.csh.intraspect.data.instruction.math.add;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x63, mnemonic = "dadd")
public final class DAddInstruction extends AddInstruction<Double> {

    @AssembleInject
    public DAddInstruction() {

    }

    public static DAddInstruction read(final DataInputStream in) {
        return new DAddInstruction();
    }
}
