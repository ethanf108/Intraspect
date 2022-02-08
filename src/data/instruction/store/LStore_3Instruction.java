package data.instruction.store;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x42, mnemonic = "lstore_3")
public final class LStore_3Instruction extends LStoreInstruction {

    public LStore_3Instruction() {
        super(3);
    }

    public static LStore_3Instruction read(final DataInputStream in) throws IOException {
        return new LStore_3Instruction();
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
