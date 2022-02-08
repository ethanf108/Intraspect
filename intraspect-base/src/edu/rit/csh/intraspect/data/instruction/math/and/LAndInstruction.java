package edu.rit.csh.intraspect.data.instruction.math.and;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x7f, mnemonic = "land")
public final class LAndInstruction extends AndInstruction<Long> {

    public LAndInstruction() {

    }

    public static LAndInstruction read(final DataInputStream in) {
        return new LAndInstruction();
    }
}
