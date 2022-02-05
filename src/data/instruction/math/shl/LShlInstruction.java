package data.instruction.math.shl;

import data.instruction.Opcode;
import data.instruction.math.ShlInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x79, mnemonic = "lshl")
public final class LShlInstruction extends ShlInstruction<Long> {

    public LShlInstruction() {

    }

    public LShlInstruction read(final DataInputStream in) {
        return new LShlInstruction();
    }
}
