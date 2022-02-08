package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1D, mnemonic = "iload_3")
public final class ILoad_3Instruction extends ILoadInstruction {

    public ILoad_3Instruction() {
        super(3);
    }

    public static ILoad_3Instruction read(final DataInputStream in) throws IOException {
        return new ILoad_3Instruction();
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
