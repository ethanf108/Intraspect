package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3E, mnemonic = "istore_3")
public final class IStore_3Instruction extends IStoreInstruction {

    public IStore_3Instruction() {
        super(3);
    }

    public static IStore_3Instruction read(final DataInputStream in) throws IOException {
        return new IStore_3Instruction();
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
