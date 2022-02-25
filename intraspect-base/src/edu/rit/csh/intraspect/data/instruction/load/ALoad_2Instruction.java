package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2C, mnemonic = "aload_2")
public final class ALoad_2Instruction extends ALoadInstruction {

    @AssembleInject
    public ALoad_2Instruction() {
        super(2);
    }

    public static ALoad_2Instruction read(final DataInputStream in) throws IOException {
        return new ALoad_2Instruction();
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
