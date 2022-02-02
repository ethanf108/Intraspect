package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x43, mnemonic = "fstore_0")
public final class FStore_0Instruction extends FStoreInstruction {

    public FStore_0Instruction() {
        super(0);
    }

    public static FStore_0Instruction read(DataInputStream in) throws IOException {
        return new FStore_0Instruction();
    }
}
