package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x35, mnemonic = "saload")
public final class SALoadInstruction extends ArrayLoadInstruction {

    public SALoadInstruction() {
    }

    public static SALoadInstruction read(DataInputStream in) throws IOException {
        return new SALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return short.class;
    }
}