package edu.rit.csh.intraspect.data.instruction.math.rem;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x72, mnemonic = "frem")
public final class FRemInstruction extends RemInstruction<Float> {

    public FRemInstruction() {

    }

    public static FRemInstruction read(final DataInputStream in) {
        return new FRemInstruction();
    }
}
