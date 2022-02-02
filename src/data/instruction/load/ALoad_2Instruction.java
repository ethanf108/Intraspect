package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x2C, mnemonic = "aload_2")
public final class ALoad_2Instruction extends ALoadInstruction {

    public ALoad_2Instruction() {
        super(2);
    }

    public static ALoad_2Instruction read(DataInputStream in) throws IOException {
        return new ALoad_2Instruction();
    }
}
