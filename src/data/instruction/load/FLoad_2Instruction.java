package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x24, mnemonic = "fload_2")
public final class FLoad_2Instruction extends FLoadInstruction {

    public FLoad_2Instruction() {
        super(2);
    }

    public static FLoad_2Instruction read(final DataInputStream in) throws IOException {
        return new FLoad_2Instruction();
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
