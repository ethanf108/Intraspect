package data.instruction.control;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB0, mnemonic = "areturn")
public final class AReturnInstruction extends ReturnInstruction {

    public AReturnInstruction() {
    }

    public static AReturnInstruction read(DataInputStream in) throws IOException {
        return new AReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }
}
