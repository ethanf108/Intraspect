package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x41, mnemonic = "lstore_2")
public final class LStore_2Instruction extends LStoreInstruction {

    @AssembleInject
    public LStore_2Instruction() {
        super(2);
    }

    public static LStore_2Instruction read(final DataInputStream in) throws IOException {
        return new LStore_2Instruction();
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
