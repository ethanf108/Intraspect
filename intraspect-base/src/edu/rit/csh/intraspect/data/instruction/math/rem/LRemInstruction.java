package edu.rit.csh.intraspect.data.instruction.math.rem;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x71, mnemonic = "lrem")
public final class LRemInstruction extends RemInstruction<Long> {

    public LRemInstruction() {

    }

    public static LRemInstruction read(final DataInputStream in) {
        return new LRemInstruction();
    }
}
