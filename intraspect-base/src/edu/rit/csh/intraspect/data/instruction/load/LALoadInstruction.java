package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2F, mnemonic = "laload")
public final class LALoadInstruction extends ArrayLoadInstruction {

    public LALoadInstruction() {
    }

    public static LALoadInstruction read(final DataInputStream in) throws IOException {
        return new LALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
