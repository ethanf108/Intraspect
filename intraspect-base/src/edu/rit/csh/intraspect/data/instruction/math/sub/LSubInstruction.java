package edu.rit.csh.intraspect.data.instruction.math.sub;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x65, mnemonic = "lsub")
public final class LSubInstruction extends SubInstruction<Long> {

    @AssembleInject
    public LSubInstruction() {

    }

    public static LSubInstruction read(final DataInputStream in) {
        return new LSubInstruction();
    }
}
