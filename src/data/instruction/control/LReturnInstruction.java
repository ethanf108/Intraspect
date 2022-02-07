package data.instruction.control;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAD, mnemonic = "lreturn")
public final class LReturnInstruction extends ReturnInstruction {

    public LReturnInstruction() {
    }

    public static LReturnInstruction read(DataInputStream in) throws IOException {
        return new LReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return long.class;
    }
}
