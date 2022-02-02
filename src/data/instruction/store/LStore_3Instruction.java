package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x42, mnemonic = "lstore_3")
public final class LStore_3Instruction extends LStoreInstruction {

    public LStore_3Instruction() {
        super(3);
    }

    public static LStore_3Instruction read(DataInputStream in) throws IOException {
        return new LStore_3Instruction();
    }
}
