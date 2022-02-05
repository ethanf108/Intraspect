package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x26, mnemonic = "dload_0")
public final class DLoad_0Instruction extends DLoadInstruction {

    public DLoad_0Instruction() {
        super(0);
    }

    public static DLoad_0Instruction read(DataInputStream in) throws IOException {
        return new DLoad_0Instruction();
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
