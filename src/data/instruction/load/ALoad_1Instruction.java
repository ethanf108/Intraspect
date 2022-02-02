package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2B, mnemonic = "aload_1")
public final class ALoad_1Instruction extends ALoadInstruction {

    public ALoad_1Instruction() {
        super(1);
    }

    public static ALoad_1Instruction read(DataInputStream in) throws IOException {
        return new ALoad_1Instruction();
    }
}
