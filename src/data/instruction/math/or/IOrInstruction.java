package data.instruction.math.or;

import data.instruction.Opcode;
import data.instruction.math.OrInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x80, mnemonic = "ior")
public final class IOrInstruction extends OrInstruction<Integer> {

    public IOrInstruction() {

    }

    public static IOrInstruction read(final DataInputStream in) {
        return new IOrInstruction();
    }
}
