package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2E, mnemonic = "iaload")
public final class IALoadInstruction extends ArrayLoadInstruction {

    public IALoadInstruction() {

    }

    public static IALoadInstruction read(final DataInputStream in) throws IOException {
        return new IALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }
}
