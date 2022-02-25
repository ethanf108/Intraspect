package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x44, mnemonic = "fstore_1")
public final class FStore_1Instruction extends FStoreInstruction {

    @AssembleInject
    public FStore_1Instruction() {
        super(1);
    }

    public static FStore_1Instruction read(final DataInputStream in) throws IOException {
        return new FStore_1Instruction();
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
