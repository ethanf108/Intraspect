package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.ArrayLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x31, mnemonic = "daload")
public final class DALoadInstruction extends ArrayLoadInstruction {

    public DALoadInstruction() {

    }

    public static DALoadInstruction read(DataInputStream in) throws IOException {
        return new DALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return double.class;
    }
}
