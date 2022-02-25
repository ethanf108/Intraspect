package edu.rit.csh.intraspect.data.instruction.math.rem;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x71, mnemonic = "lrem")
public final class LRemInstruction extends RemInstruction<Long> {

    @AssembleInject
    public LRemInstruction() {

    }

    public static LRemInstruction read(final DataInputStream in) {
        return new LRemInstruction();
    }
}
