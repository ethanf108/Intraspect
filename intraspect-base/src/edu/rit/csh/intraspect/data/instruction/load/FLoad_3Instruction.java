package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x25, mnemonic = "fload_3")
public final class FLoad_3Instruction extends FLoadInstruction {

    public FLoad_3Instruction() {
        super(3);
    }

    public static FLoad_3Instruction read(final DataInputStream in) throws IOException {
        return new FLoad_3Instruction();
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
