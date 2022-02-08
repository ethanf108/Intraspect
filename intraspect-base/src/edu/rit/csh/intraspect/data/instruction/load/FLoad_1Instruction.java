package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x23, mnemonic = "fload_1")
public final class FLoad_1Instruction extends FLoadInstruction {

    public FLoad_1Instruction() {
        super(1);
    }

    public static FLoad_1Instruction read(final DataInputStream in) throws IOException {
        return new FLoad_1Instruction();
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
