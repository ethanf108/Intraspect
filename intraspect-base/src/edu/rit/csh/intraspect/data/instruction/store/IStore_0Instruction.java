package edu.rit.csh.intraspect.data.instruction.store;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x3B, mnemonic = "istore_0")
public final class IStore_0Instruction extends IStoreInstruction {

    @AssembleInject
    public IStore_0Instruction() {
        super(0);
    }

    public static IStore_0Instruction read(final DataInputStream in) throws IOException {
        return new IStore_0Instruction();
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
