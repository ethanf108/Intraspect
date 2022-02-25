package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4C, mnemonic = "astore_1")
public final class AStore_1Instruction extends AStoreInstruction {

    @AssembleInject
    public AStore_1Instruction() {
        super(1);
    }

    public static AStore_1Instruction read(final DataInputStream in) throws IOException {
        return new AStore_1Instruction();
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
