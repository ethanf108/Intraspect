package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1E, mnemonic = "lload_0")
public final class LLoad_0Instruction extends LLoadInstruction {

    public LLoad_0Instruction() {
        super(0);
    }

    public static LLoad_0Instruction read(final DataInputStream in) throws IOException {
        return new LLoad_0Instruction();
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
