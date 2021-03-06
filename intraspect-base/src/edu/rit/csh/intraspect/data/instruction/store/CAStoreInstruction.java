package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x55, mnemonic = "castore")
public final class CAStoreInstruction extends ArrayStoreInstruction {

    public CAStoreInstruction() {
    }

    public static CAStoreInstruction read(final DataInputStream in) throws IOException {
        return new CAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return char.class;
    }
}
