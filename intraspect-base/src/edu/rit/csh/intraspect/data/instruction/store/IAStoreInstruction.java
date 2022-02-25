package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4F, mnemonic = "iastore")
public final class IAStoreInstruction extends ArrayStoreInstruction {

    @AssembleInject
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
