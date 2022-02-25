package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x28, mnemonic = "dload_2")
public final class DLoad_2Instruction extends DLoadInstruction {

    @AssembleInject
    public DLoad_2Instruction() {
        super(2);
    }

    public static DLoad_2Instruction read(final DataInputStream in) throws IOException {
        return new DLoad_2Instruction();
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
