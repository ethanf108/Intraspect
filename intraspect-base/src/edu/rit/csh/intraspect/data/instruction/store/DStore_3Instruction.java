package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4A, mnemonic = "dstore_3")
public final class DStore_3Instruction extends DStoreInstruction {

    @AssembleInject
    public DStore_3Instruction() {
        super(3);
    }

    public static DStore_3Instruction read(final DataInputStream in) throws IOException {
        return new DStore_3Instruction();
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
