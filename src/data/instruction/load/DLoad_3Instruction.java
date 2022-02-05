package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x29, mnemonic = "dload_3")
public final class DLoad_3Instruction extends DLoadInstruction {

    public DLoad_3Instruction() {
        super(3);
    }

    public static DLoad_3Instruction read(DataInputStream in) throws IOException {
        return new DLoad_3Instruction();
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
