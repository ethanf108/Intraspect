package data.instruction.math.mul;

import data.instruction.Opcode;
import data.instruction.math.MulInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x68, mnemonic = "imul")
public final class IMulInstruction extends MulInstruction<Integer> {

    public IMulInstruction() {

    }

    public IMulInstruction read(final DataInputStream in) {
        return new IMulInstruction();
    }
}
