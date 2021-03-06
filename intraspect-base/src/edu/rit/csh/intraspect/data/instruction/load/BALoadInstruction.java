package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x33, mnemonic = "baload")
public final class BALoadInstruction extends ArrayLoadInstruction {

    public BALoadInstruction() {

    }

    public static BALoadInstruction read(final DataInputStream in) throws IOException {
        return new BALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return byte.class;
    }
}
