package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x53, mnemonic = "aastore")
public final class AAStoreInstruction extends ArrayStoreInstruction {

    @AssembleInject
    public AAStoreInstruction() {

    }

    public static AAStoreInstruction read(final DataInputStream in) throws IOException {
        return new AAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }
}
