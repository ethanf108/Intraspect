package edu.rit.csh.intraspect.data.instruction.math.ushr;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x7c, mnemonic = "iushr")
public final class IushrInstruction extends UshrInstruction<Integer> {

    @AssembleInject
    public IushrInstruction() {

    }

    public static IushrInstruction read(final DataInputStream in) {
        return new IushrInstruction();
    }
}
