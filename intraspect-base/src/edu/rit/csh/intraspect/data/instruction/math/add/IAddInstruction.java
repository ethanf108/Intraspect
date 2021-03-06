package edu.rit.csh.intraspect.data.instruction.math.add;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x60, mnemonic = "iadd")
public final class IAddInstruction extends AddInstruction<Integer> {

    public IAddInstruction() {

    }

    public static IAddInstruction read(final DataInputStream in) {
        return new IAddInstruction();
    }
}
