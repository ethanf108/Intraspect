package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3D, mnemonic = "istore_2")
public final class IStore_2Instruction extends IStoreInstruction {

    public IStore_2Instruction() {
        super(2);
    }

    public static IStore_2Instruction read(final DataInputStream in) throws IOException {
        return new IStore_2Instruction();
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
