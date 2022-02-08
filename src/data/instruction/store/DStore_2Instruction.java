package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x49, mnemonic = "dstore_2")
public final class DStore_2Instruction extends DStoreInstruction {

    public DStore_2Instruction() {
        super(2);
    }

    public static DStore_2Instruction read(final DataInputStream in) throws IOException {
        return new DStore_2Instruction();
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
