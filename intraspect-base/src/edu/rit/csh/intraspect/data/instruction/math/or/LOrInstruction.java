package edu.rit.csh.intraspect.data.instruction.math.or;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x81, mnemonic = "lor")
public final class LOrInstruction extends OrInstruction<Long> {

    @AssembleInject
    public LOrInstruction() {

    }

    public static LOrInstruction read(final DataInputStream in) {
        return new LOrInstruction();
    }
}
