package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x27, mnemonic = "dload_1")
public final class DLoad_1Instruction extends DLoadInstruction {

    @AssembleInject
    public DLoad_1Instruction() {
        super(1);
    }

    public static DLoad_1Instruction read(final DataInputStream in) throws IOException {
        return new DLoad_1Instruction();
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
