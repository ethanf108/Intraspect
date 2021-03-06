package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3f, mnemonic = "lstore_0")
public final class LStore_0Instruction extends LStoreInstruction {

    public LStore_0Instruction() {
        super(0);
    }

    public static LStore_0Instruction read(final DataInputStream in) throws IOException {
        return new LStore_0Instruction();
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
