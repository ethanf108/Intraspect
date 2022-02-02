package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1D, mnemonic = "iload_3")
public final class ILoad_3Instruction extends ILoadInstruction {

    public ILoad_3Instruction() {
        super(3);
    }

    public static ILoad_3Instruction read(DataInputStream in) throws IOException {
        return new ILoad_3Instruction();
    }
}
