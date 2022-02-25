package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x35, mnemonic = "saload")
public final class SALoadInstruction extends ArrayLoadInstruction {

    @AssembleInject
    public SALoadInstruction() {
    }

    public static SALoadInstruction read(final DataInputStream in) throws IOException {
        return new SALoadInstruction();
    }

    @Override
    public Class<?> getType() {
        return short.class;
    }
}
