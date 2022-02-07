package data.instruction.math.shl;

import data.instruction.Opcode;
import data.instruction.math.ShlInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x78, mnemonic = "ishl")
public final class IShlInstruction extends ShlInstruction<Integer> {

    public IShlInstruction() {

    }

    public static IShlInstruction read(final DataInputStream in) {
        return new IShlInstruction();
    }
}
