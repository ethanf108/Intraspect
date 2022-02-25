package edu.rit.csh.intraspect.data.instruction.math.div;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x6d, mnemonic = "ldiv")
public final class LDivInstruction extends DivInstruction<Long> {

    @AssembleInject
    public LDivInstruction() {

    }

    public static LDivInstruction read(final DataInputStream in) {
        return new LDivInstruction();
    }
}
