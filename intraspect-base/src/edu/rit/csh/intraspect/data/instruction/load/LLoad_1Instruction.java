package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1F, mnemonic = "lload_1")
public final class LLoad_1Instruction extends LLoadInstruction {

    @AssembleInject
    public LLoad_1Instruction() {
        super(1);
    }

    public static LLoad_1Instruction read(final DataInputStream in) throws IOException {
        return new LLoad_1Instruction();
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
