package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x20, mnemonic = "lload_2")
public final class LLoad_2Instruction extends LLoadInstruction {

    @AssembleInject
    public LLoad_2Instruction() {
        super(2);
    }

    public static LLoad_2Instruction read(final DataInputStream in) throws IOException {
        return new LLoad_2Instruction();
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
