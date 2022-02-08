package edu.rit.csh.intraspect.data.instruction.math.mul;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;

@Opcode(opcode = 0x69, mnemonic = "lmul")
public final class LMulInstruction extends MulInstruction<Long> {

    public LMulInstruction() {

    }

    public static LMulInstruction read(final DataInputStream in) {
        return new LMulInstruction();
    }
}
