package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x48, mnemonic = "dstore_1")
public final class DStore_1Instruction extends DStoreInstruction {

    public DStore_1Instruction() {
        super(1);
    }

    public static DStore_1Instruction read(DataInputStream in) throws IOException {
        return new DStore_1Instruction();
    }
}
