package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2D, mnemonic = "aload_3")
public final class ALoad_3Instruction extends ALoadInstruction {

    public ALoad_3Instruction() {
        super(3);
    }

    public static ALoad_3Instruction read(DataInputStream in) throws IOException {
        return new ALoad_3Instruction();
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
