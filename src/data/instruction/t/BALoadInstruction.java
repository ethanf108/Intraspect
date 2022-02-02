package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.ArrayLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x33, mnemonic = "baload")
public final class BALoadInstruction extends ArrayLoadInstruction {

    public BALoadInstruction() {

    }

    public static BALoadInstruction read(DataInputStream in) throws IOException {
        return new BALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return byte.class;
    }
}
