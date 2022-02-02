package data.instruction.t;

import data.instruction.Opcode;
import data.instruction.load.DLoadInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x28, mnemonic = "dload_2")
public final class DLoad_2Instruction extends DLoadInstruction {

    public DLoad_2Instruction() {
        super(2);
    }

    public static DLoad_2Instruction read(DataInputStream in) throws IOException {
        return new DLoad_2Instruction();
    }
}
