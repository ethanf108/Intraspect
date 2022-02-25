package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1B, mnemonic = "iload_1")
public final class ILoad_1Instruction extends ILoadInstruction {

    @AssembleInject
    public ILoad_1Instruction() {
        super(1);
    }

    public static ILoad_1Instruction read(final DataInputStream in) throws IOException {
        return new ILoad_1Instruction();
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
