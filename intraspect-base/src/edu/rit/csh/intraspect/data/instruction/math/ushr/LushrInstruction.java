package edu.rit.csh.intraspect.data.instruction.math.ushr;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x7d, mnemonic = "lushr")
public final class LushrInstruction extends UshrInstruction<Long> {

    public LushrInstruction() {

    }

    public static LushrInstruction read(final DataInputStream in) {
        return new LushrInstruction();
    }
}
