package edu.rit.csh.intraspect.data.instruction.control;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAF, mnemonic = "dreturn")
public final class DReturnInstruction extends ReturnInstruction {

    @AssembleInject
    public DReturnInstruction() {
    }

    public static DReturnInstruction read(final DataInputStream in) throws IOException {
        return new DReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return double.class;
    }
}
