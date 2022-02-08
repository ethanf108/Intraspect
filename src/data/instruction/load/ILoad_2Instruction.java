package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1C, mnemonic = "iload_2")
public final class ILoad_2Instruction extends ILoadInstruction {

    public ILoad_2Instruction() {
        super(2);
    }

    public static ILoad_2Instruction read(final DataInputStream in) throws IOException {
        return new ILoad_2Instruction();
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
