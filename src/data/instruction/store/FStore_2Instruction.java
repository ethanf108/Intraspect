package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x45, mnemonic = "fstore_2")
public final class FStore_2Instruction extends FStoreInstruction {

    public FStore_2Instruction() {
        super(2);
    }

    public static FStore_2Instruction read(DataInputStream in) throws IOException {
        return new FStore_2Instruction();
    }
}
