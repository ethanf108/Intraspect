package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x56, mnemonic = "sastore")
public final class SAStoreInstruction extends ArrayStoreInstruction {

    public SAStoreInstruction() {
    }

    public static SAStoreInstruction read(DataInputStream in) throws IOException {
        return new SAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return short.class;
    }
}
