package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x56, mnemonic = "sastore")
public final class SAStoreInstruction extends ArrayStoreInstruction {

    @AssembleInject
    public SAStoreInstruction() {
    }

    public static SAStoreInstruction read(final DataInputStream in) throws IOException {
        return new SAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return short.class;
    }
}
