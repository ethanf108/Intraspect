package data.instruction.control;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xAC, mnemonic = "ireturn")
public final class IReturnInstruction extends ReturnInstruction {

    public IReturnInstruction() {
    }

    public static IReturnInstruction read(DataInputStream in) throws IOException {
        return new IReturnInstruction();
    }

    @Override
    public Class<?> getReturnType() {
        return int.class;
    }
}
