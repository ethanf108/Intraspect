package edu.rit.csh.intraspect.data.instruction.math.mul;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x6b, mnemonic = "dmul")
public final class DMulInstruction extends MulInstruction<Double> {

    public DMulInstruction() {

    }

    public static DMulInstruction read(final DataInputStream in) {
        return new DMulInstruction();
    }
}
