package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x47, mnemonic = "dstore_0")
public final class DStore_0Instruction extends DStoreInstruction {

    public DStore_0Instruction() {
        super(0);
    }

    public static DStore_0Instruction read(DataInputStream in) throws IOException {
        return new DStore_0Instruction();
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }
}
