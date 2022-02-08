package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x21, mnemonic = "lload_3")
public final class LLoad_3Instruction extends LLoadInstruction {

    public LLoad_3Instruction() {
        super(3);
    }

    public static LLoad_3Instruction read(final DataInputStream in) throws IOException {
        return new LLoad_3Instruction();
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
