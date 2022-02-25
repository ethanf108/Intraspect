package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2A, mnemonic = "aload_0")
public final class ALoad_0Instruction extends ALoadInstruction {

    @AssembleInject
    public ALoad_0Instruction() {
        super(0);
    }

    public static ALoad_0Instruction read(final DataInputStream in) throws IOException {
        return new ALoad_0Instruction();
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
