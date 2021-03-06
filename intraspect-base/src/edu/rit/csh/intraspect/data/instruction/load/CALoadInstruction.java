package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x34, mnemonic = "caload")
public final class CALoadInstruction extends ArrayLoadInstruction {

    public CALoadInstruction() {
    }

    public static CALoadInstruction read(final DataInputStream in) throws IOException {
        return new CALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return char.class;
    }
}
