package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x4E, mnemonic = "astore_3")
public final class AStore_3Instruction extends AStoreInstruction {

    @AssembleInject
    public AStore_3Instruction() {
        super(3);
    }

    public static AStore_3Instruction read(final DataInputStream in) throws IOException {
        return new AStore_3Instruction();
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
