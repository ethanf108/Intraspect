package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x31, mnemonic = "daload")
public final class DALoadInstruction extends ArrayLoadInstruction {

    public DALoadInstruction() {

    }

    public static DALoadInstruction read(final DataInputStream in) throws IOException {
        return new DALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
