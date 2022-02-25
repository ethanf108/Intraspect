package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x30, mnemonic = "faload")
public final class FALoadInstruction extends ArrayLoadInstruction {

    @AssembleInject
    public FALoadInstruction() {
    }

    public static FALoadInstruction read(final DataInputStream in) throws IOException {
        return new FALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return float.class;
    }
}
