package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4B, mnemonic = "astore_0")
public final class AStore_0Instruction extends AStoreInstruction {

    @AssembleInject
    public AStore_0Instruction() {
        super(0);
    }

    public static AStore_0Instruction read(final DataInputStream in) throws IOException {
        return new AStore_0Instruction();
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
