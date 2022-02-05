package data.instruction.math.and;

import data.instruction.Opcode;
import data.instruction.math.AndInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x7f, mnemonic = "land")
public final class LAndInstruction extends AndInstruction<Long> {

    public LAndInstruction() {

    }

    public LAndInstruction read(final DataInputStream in) {
        return new LAndInstruction();
    }
}
