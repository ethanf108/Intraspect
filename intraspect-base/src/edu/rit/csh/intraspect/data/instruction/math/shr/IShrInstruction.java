package edu.rit.csh.intraspect.data.instruction.math.shr;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x7a, mnemonic = "ishr")
public final class IShrInstruction extends ShrInstruction<Integer> {

    public IShrInstruction() {

    }

    public static IShrInstruction read(final DataInputStream in) {
        return new IShrInstruction();
    }
}
