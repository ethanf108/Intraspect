package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2A, mnemonic = "aload_0")
public final class ALoad_0Instruction extends ALoadInstruction {

    public ALoad_0Instruction() {
        super(0);
    }

    public static ALoad_0Instruction read(DataInputStream in) throws IOException {
        return new ALoad_0Instruction();
    }
}