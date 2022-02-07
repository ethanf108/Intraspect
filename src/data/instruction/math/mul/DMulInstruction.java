package data.instruction.math.mul;

import data.instruction.Opcode;
import data.instruction.math.MulInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x6b, mnemonic = "dmul")
public final class DMulInstruction extends MulInstruction<Double> {

    public DMulInstruction() {

    }

    public static DMulInstruction read(final DataInputStream in) {
        return new DMulInstruction();
    }
}
