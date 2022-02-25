package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x24, mnemonic = "fload_2")
public final class FLoad_2Instruction extends FLoadInstruction {

    @AssembleInject
    public FLoad_2Instruction() {
        super(2);
    }

    public static FLoad_2Instruction read(final DataInputStream in) throws IOException {
        return new FLoad_2Instruction();
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
