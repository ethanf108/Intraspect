package edu.rit.csh.intraspect.data.instruction.reserved;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xFF, mnemonic = "impdep2")
public final class ImpDep2Instruction extends ReservedInstruction {

    public ImpDep2Instruction() {

    }

    public static ImpDep2Instruction read(final DataInputStream in) throws IOException {
        return new ImpDep2Instruction();
    }
}
