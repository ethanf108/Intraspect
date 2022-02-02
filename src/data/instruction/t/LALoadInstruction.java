package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.ArrayLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2F, mnemonic = "laload")
public final class LALoadInstruction extends ArrayLoadInstruction {

    public LALoadInstruction() {
    }

    public static LALoadInstruction read(DataInputStream in) throws IOException {
        return new LALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
