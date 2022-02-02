package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x44, mnemonic = "fstore_1")
public final class FStore_1Instruction extends FStoreInstruction {

    public FStore_1Instruction() {
        super(1);
    }

    public static FStore_1Instruction read(DataInputStream in) throws IOException {
        return new FStore_1Instruction();
    }
}
