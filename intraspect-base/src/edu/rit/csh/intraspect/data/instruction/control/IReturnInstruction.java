package edu.rit.csh.intraspect.data.instruction.control;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAC, mnemonic = "ireturn")
public final class IReturnInstruction extends ReturnInstruction {

    public IReturnInstruction() {
    }

    public static IReturnInstruction read(final DataInputStream in) throws IOException {
        return new IReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return int.class;
    }
}
