package data.instruction.math.mul;

import data.instruction.Opcode;
import data.instruction.math.MulInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x6a, mnemonic = "fmul")
public final class FMulInstruction extends MulInstruction<Float> {

    public FMulInstruction() {

    }

    public static FMulInstruction read(final DataInputStream in) {
        return new FMulInstruction();
    }
}
