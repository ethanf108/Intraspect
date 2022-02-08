package edu.rit.csh.intraspect.data.instruction.math.shl;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x78, mnemonic = "ishl")
public final class IShlInstruction extends ShlInstruction<Integer> {

    public IShlInstruction() {

    }

    public static IShlInstruction read(final DataInputStream in) {
        return new IShlInstruction();
    }
}
