package data.instruction.math.ushr;

import data.instruction.Opcode;
import data.instruction.math.UshrInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x7d, mnemonic = "lushr")
public final class LushrInstruction extends UshrInstruction<Long> {

    public LushrInstruction() {

    }

    public static LushrInstruction read(final DataInputStream in) {
        return new LushrInstruction();
    }
}
