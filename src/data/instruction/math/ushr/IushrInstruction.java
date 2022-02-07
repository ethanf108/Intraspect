package data.instruction.math.ushr;

import data.instruction.Opcode;
import data.instruction.math.UshrInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x7c, mnemonic = "iushr")
public final class IushrInstruction extends UshrInstruction<Integer> {

    public IushrInstruction() {

    }

    public static IushrInstruction read(final DataInputStream in) {
        return new IushrInstruction();
    }
}
