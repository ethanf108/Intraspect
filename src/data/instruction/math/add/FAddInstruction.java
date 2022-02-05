package data.instruction.math.add;

import data.instruction.Opcode;
import data.instruction.math.AddInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x62, mnemonic = "fadd")
public final class FAddInstruction extends AddInstruction<Float> {

    public FAddInstruction() {

    }

    public FAddInstruction read(final DataInputStream in) {
        return new FAddInstruction();
    }
}
