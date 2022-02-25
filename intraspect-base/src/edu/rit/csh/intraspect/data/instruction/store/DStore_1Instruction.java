package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x48, mnemonic = "dstore_1")
public final class DStore_1Instruction extends DStoreInstruction {

    @AssembleInject
    public DStore_1Instruction() {
        super(1);
    }

    public static DStore_1Instruction read(final DataInputStream in) throws IOException {
        return new DStore_1Instruction();
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
