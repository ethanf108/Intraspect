package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x1F, mnemonic = "lload_1")
public final class LLoad_1Instruction extends LLoadInstruction {

    public LLoad_1Instruction() {
        super(1);
    }

    public static LLoad_1Instruction read(DataInputStream in) throws IOException {
        return new LLoad_1Instruction();
    }
}