package data.instruction.reserved;

import data.instruction.Opcode;
import data.instruction.ReservedInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xFF, mnemonic = "impdep2")
public final class ImpDep2Instruction extends ReservedInstruction {

    public ImpDep2Instruction() {

    }

    public static ImpDep2Instruction read(DataInputStream in) throws IOException {
        return new ImpDep2Instruction();
    }
}
