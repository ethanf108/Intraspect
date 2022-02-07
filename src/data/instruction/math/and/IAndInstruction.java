package data.instruction.math.and;

import data.instruction.Opcode;
import data.instruction.math.AndInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x7e, mnemonic = "iand")
public final class IAndInstruction extends AndInstruction<Integer> {

    public IAndInstruction() {

    }

    public static IAndInstruction read(final DataInputStream in) {
        return new IAndInstruction();
    }
}
