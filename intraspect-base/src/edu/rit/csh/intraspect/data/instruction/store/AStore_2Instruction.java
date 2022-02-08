package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4D, mnemonic = "astore_2")
public final class AStore_2Instruction extends AStoreInstruction {

    public AStore_2Instruction() {
        super(2);
    }

    public static AStore_2Instruction read(final DataInputStream in) throws IOException {
        return new AStore_2Instruction();
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
