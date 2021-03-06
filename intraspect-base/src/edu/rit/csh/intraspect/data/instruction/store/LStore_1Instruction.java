package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x40, mnemonic = "lstore_1")
public final class LStore_1Instruction extends LStoreInstruction {

    public LStore_1Instruction() {
        super(1);
    }

    public static LStore_1Instruction read(final DataInputStream in) throws IOException {
        return new LStore_1Instruction();
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
