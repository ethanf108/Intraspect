package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4C, mnemonic = "astore_1")
public final class AStore_1Instruction extends AStoreInstruction {

    public AStore_1Instruction() {
        super(1);
    }

    public static AStore_1Instruction read(DataInputStream in) throws IOException {
        return new AStore_1Instruction();
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
