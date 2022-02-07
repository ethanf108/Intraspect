package data.instruction.math.or;

import data.instruction.Opcode;
import data.instruction.math.OrInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x81, mnemonic = "lor")
public final class LOrInstruction extends OrInstruction<Long> {

    public LOrInstruction() {

    }

    public static LOrInstruction read(final DataInputStream in) {
        return new LOrInstruction();
    }
}
