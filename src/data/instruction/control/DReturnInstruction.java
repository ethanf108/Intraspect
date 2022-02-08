package data.instruction.control;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAF, mnemonic = "dreturn")
public final class DReturnInstruction extends ReturnInstruction {

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
