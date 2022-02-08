package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x50, mnemonic = "lastore")
public final class LAStoreInstruction extends ArrayStoreInstruction {

    public LAStoreInstruction() {
    }

    public static LAStoreInstruction read(final DataInputStream in) throws IOException {
        return new LAStoreInstruction();
    }

    @Override
    public Class<?> getType() {
        return long.class;
    }
}
