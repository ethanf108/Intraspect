package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3B, mnemonic = "istore_0")
public final class IStore_0Instruction extends IStoreInstruction {

    public IStore_0Instruction() {
        super(0);
    }

    public static IStore_0Instruction read(DataInputStream in) throws IOException {
        return new IStore_0Instruction();
    }
}
