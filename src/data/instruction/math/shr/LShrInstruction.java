package data.instruction.math.shr;

import data.instruction.Opcode;
import data.instruction.math.ShrInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x7b, mnemonic = "lshr")
public final class LShrInstruction extends ShrInstruction<Long> {

    public LShrInstruction() {

    }

    public static LShrInstruction read(final DataInputStream in) {
        return new LShrInstruction();
    }
}
