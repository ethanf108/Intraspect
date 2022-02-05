package data.instruction.math.add;

import data.instruction.Opcode;
import data.instruction.math.AddInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x61, mnemonic = "ladd")
public final class LAddInstruction extends AddInstruction<Long> {

    public LAddInstruction() {

    }

    public LAddInstruction read(final DataInputStream in) {
        return new LAddInstruction();
    }
}
