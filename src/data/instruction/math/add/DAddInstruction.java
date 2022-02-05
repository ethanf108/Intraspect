package data.instruction.math.add;

import data.instruction.Opcode;
import data.instruction.math.AddInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x63, mnemonic = "dadd")
public final class DAddInstruction extends AddInstruction<Double> {

    public DAddInstruction() {

    }

    public DAddInstruction read(final DataInputStream in) {
        return new DAddInstruction();
    }
}
