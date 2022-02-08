package edu.rit.csh.intraspect.data.instruction.math.sub;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x67, mnemonic = "dsub")
public final class DSubInstruction extends SubInstruction<Double> {

    public DSubInstruction() {

    }

    public static DSubInstruction read(final DataInputStream in) {
        return new DSubInstruction();
    }
}
