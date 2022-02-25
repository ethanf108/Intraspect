package edu.rit.csh.intraspect.data.instruction.math.rem;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x70, mnemonic = "irem")
public final class IRemInstruction extends RemInstruction<Integer> {

    @AssembleInject
    public IRemInstruction() {

    }

    public static IRemInstruction read(final DataInputStream in) {
        return new IRemInstruction();
    }
}
