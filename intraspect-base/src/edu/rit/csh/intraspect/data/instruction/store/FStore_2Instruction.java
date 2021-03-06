package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x45, mnemonic = "fstore_2")
public final class FStore_2Instruction extends FStoreInstruction {

    public FStore_2Instruction() {
        super(2);
    }

    public static FStore_2Instruction read(final DataInputStream in) throws IOException {
        return new FStore_2Instruction();
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
