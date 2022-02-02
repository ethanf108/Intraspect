package data.instruction.load;

import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x20, mnemonic = "lload_2")
public final class LLoad_2Instruction extends LLoadInstruction {

    public LLoad_2Instruction() {
        super(2);
    }

    public static LLoad_2Instruction read(DataInputStream in) throws IOException {
        return new LLoad_2Instruction();
    }
}
