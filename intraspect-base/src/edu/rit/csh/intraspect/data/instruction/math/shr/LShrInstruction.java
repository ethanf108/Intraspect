package edu.rit.csh.intraspect.data.instruction.math.shr;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x7b, mnemonic = "lshr")
public final class LShrInstruction extends ShrInstruction<Long> {

    @AssembleInject
    public LShrInstruction() {

    }

    public static LShrInstruction read(final DataInputStream in) {
        return new LShrInstruction();
    }
}
