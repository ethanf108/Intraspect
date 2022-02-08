package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3C, mnemonic = "istore_1")
public final class IStore_1Instruction extends IStoreInstruction {

    public IStore_1Instruction() {
        super(1);
    }

    public static IStore_1Instruction read(final DataInputStream in) throws IOException {
        return new IStore_1Instruction();
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
