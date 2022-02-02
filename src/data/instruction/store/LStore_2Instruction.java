package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x41, mnemonic = "lstore_2")
public final class LStore_2Instruction extends LStoreInstruction {

    public LStore_2Instruction() {
        super(2);
    }

    public static LStore_2Instruction read(DataInputStream in) throws IOException {
        return new LStore_2Instruction();
    }
}
