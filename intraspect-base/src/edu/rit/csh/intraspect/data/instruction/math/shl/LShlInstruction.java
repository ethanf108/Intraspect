package edu.rit.csh.intraspect.data.instruction.math.shl;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x79, mnemonic = "lshl")
public final class LShlInstruction extends ShlInstruction<Long> {

    public LShlInstruction() {

    }

    public static LShlInstruction read(final DataInputStream in) {
        return new LShlInstruction();
    }
}
