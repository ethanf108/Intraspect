package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1B, mnemonic = "iload_1")
public final class ILoad_1Instruction extends ILoadInstruction {

    public ILoad_1Instruction() {
        super(1);
    }

    public static ILoad_1Instruction read(DataInputStream in) throws IOException {
        return new ILoad_1Instruction();
    }
}
