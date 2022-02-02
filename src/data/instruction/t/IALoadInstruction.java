package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.ArrayLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2E, mnemonic = "iaload")
public final class IALoadInstruction extends ArrayLoadInstruction {

    public IALoadInstruction() {

    }

    public static IALoadInstruction read(DataInputStream in) throws IOException {
        return new IALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }
}
