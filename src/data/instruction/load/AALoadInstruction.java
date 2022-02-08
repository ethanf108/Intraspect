package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x32, mnemonic = "aaload")
public final class AALoadInstruction extends ArrayLoadInstruction {

    public AALoadInstruction() {

    }

    public static AALoadInstruction read(final DataInputStream in) throws IOException {
        return new AALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
