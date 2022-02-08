package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4F, mnemonic = "iastore")
public final class IAStoreInstruction extends ArrayStoreInstruction {

    public IAStoreInstruction() {

    }

    public static IAStoreInstruction read(final DataInputStream in) throws IOException {
        return new IAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return int.class;
    }
}
