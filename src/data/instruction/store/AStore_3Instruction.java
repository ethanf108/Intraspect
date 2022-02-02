package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4E, mnemonic = "astore_3")
public final class AStore_3Instruction extends AStoreInstruction {

    public AStore_3Instruction() {
        super(3);
    }

    public static AStore_3Instruction read(DataInputStream in) throws IOException {
        return new AStore_3Instruction();
    }
}
