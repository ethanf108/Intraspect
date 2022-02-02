package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3f, mnemonic = "lstore_0")
public final class LStore_0Instruction extends LStoreInstruction {

    public LStore_0Instruction() {
        super(0);
    }

    public static LStore_0Instruction read(DataInputStream in) throws IOException {
        return new LStore_0Instruction();
    }
}
