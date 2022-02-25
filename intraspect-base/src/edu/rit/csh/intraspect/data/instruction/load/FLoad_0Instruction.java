package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x22, mnemonic = "fload_0")
public final class FLoad_0Instruction extends FLoadInstruction {

    @AssembleInject
    public FLoad_0Instruction() {
        super(0);
    }

    public static FLoad_0Instruction read(final DataInputStream in) throws IOException {
        return new FLoad_0Instruction();
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
