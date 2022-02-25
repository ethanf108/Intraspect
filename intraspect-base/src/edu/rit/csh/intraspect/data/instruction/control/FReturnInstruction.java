package edu.rit.csh.intraspect.data.instruction.control;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAE, mnemonic = "freturn")
public final class FReturnInstruction extends ReturnInstruction {

    @AssembleInject
    public FReturnInstruction() {
    }

    public static FReturnInstruction read(final DataInputStream in) throws IOException {
        return new FReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return float.class;
    }
}
