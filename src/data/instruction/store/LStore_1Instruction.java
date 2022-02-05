package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x40, mnemonic = "lstore_1")
public final class LStore_1Instruction extends LStoreInstruction {

    public LStore_1Instruction() {
        super(1);
    }

    public static LStore_1Instruction read(DataInputStream in) throws IOException {
        return new LStore_1Instruction();
    }
}
