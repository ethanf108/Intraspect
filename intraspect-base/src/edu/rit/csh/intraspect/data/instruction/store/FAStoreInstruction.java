package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x51, mnemonic = "fastore")
public final class FAStoreInstruction extends ArrayStoreInstruction {

    public FAStoreInstruction() {
    }

    public static FAStoreInstruction read(final DataInputStream in) throws IOException {
        return new FAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
