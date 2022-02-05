package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x46, mnemonic = "fstore_3")
public final class FStore_3Instruction extends FStoreInstruction {

    public FStore_3Instruction() {
        super(3);
    }

    public static FStore_3Instruction read(DataInputStream in) throws IOException {
        return new FStore_3Instruction();
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
