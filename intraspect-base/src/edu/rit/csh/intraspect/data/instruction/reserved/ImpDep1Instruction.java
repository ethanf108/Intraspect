package edu.rit.csh.intraspect.data.instruction.reserved;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xFE, mnemonic = "impdep1")
public final class ImpDep1Instruction extends ReservedInstruction {

    public ImpDep1Instruction() {

    }

    public static ImpDep1Instruction read(final DataInputStream in) throws IOException {
        return new ImpDep1Instruction();
    }
}
