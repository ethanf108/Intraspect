package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x43, mnemonic = "fstore_0")
public final class FStore_0Instruction extends FStoreInstruction {

    @AssembleInject
    public FStore_0Instruction() {
        super(0);
    }

    public static FStore_0Instruction read(final DataInputStream in) throws IOException {
        return new FStore_0Instruction();
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
