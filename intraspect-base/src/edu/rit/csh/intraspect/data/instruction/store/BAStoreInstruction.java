package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x54, mnemonic = "bastore")
public final class BAStoreInstruction extends ArrayStoreInstruction {

    public BAStoreInstruction() {

    }

    public static BAStoreInstruction read(final DataInputStream in) throws IOException {
        return new BAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return byte.class;
    }
}
