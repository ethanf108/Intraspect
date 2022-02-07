package data.instruction.math.mul;

import data.instruction.Opcode;
import data.instruction.math.MulInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x69, mnemonic = "lmul")
public final class LMulInstruction extends MulInstruction<Long> {

    public LMulInstruction() {

    }

    public static LMulInstruction read(final DataInputStream in) {
        return new LMulInstruction();
    }
}
