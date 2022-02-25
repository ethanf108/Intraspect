package edu.rit.csh.intraspect.data.instruction.math.and;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x7f, mnemonic = "land")
public final class LAndInstruction extends AndInstruction<Long> {

    @AssembleInject
    public LAndInstruction() {

    }

    public static LAndInstruction read(final DataInputStream in) {
        return new LAndInstruction();
    }
}
