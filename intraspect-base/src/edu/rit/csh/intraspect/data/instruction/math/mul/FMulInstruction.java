package edu.rit.csh.intraspect.data.instruction.math.mul;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x6a, mnemonic = "fmul")
public final class FMulInstruction extends MulInstruction<Float> {

    public FMulInstruction() {

    }

    public static FMulInstruction read(final DataInputStream in) {
        return new FMulInstruction();
    }
}
