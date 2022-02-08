package edu.rit.csh.intraspect.data.instruction.control;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAD, mnemonic = "lreturn")
public final class LReturnInstruction extends ReturnInstruction {

    public LReturnInstruction() {
    }

    public static LReturnInstruction read(final DataInputStream in) throws IOException {
        return new LReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return long.class;
    }
}
