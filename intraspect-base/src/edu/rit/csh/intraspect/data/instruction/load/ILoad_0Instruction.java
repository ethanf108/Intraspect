package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1A, mnemonic = "iload_0")
public final class ILoad_0Instruction extends ILoadInstruction {

    public ILoad_0Instruction() {
        super(0);
    }

    public static ILoad_0Instruction read(final DataInputStream in) throws IOException {
        return new ILoad_0Instruction();
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
