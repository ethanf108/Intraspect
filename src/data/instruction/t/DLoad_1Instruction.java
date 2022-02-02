package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.DLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x27, mnemonic = "dload_1")
public final class DLoad_1Instruction extends DLoadInstruction {

    public DLoad_1Instruction() {
        super(1);
    }

    public static DLoad_1Instruction read(DataInputStream in) throws IOException {
        return new DLoad_1Instruction();
    }
}
