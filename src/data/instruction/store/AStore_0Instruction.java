package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4B, mnemonic = "astore_0")
public final class AStore_0Instruction extends AStoreInstruction {

    public AStore_0Instruction() {
        super(0);
    }

    public static AStore_0Instruction read(DataInputStream in) throws IOException {
        return new AStore_0Instruction();
    }
}
