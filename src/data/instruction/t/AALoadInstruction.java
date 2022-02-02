package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.ArrayLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x33, mnemonic = "aaload")
public final class AALoadInstruction extends ArrayLoadInstruction {

    public AALoadInstruction() {

    }

    public static AALoadInstruction read(DataInputStream in) throws IOException {
        return new AALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
