package edu.rit.csh.intraspect.data.instruction.math.sub;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x64, mnemonic = "isub")
public final class ISubInstruction extends SubInstruction<Integer> {

    @AssembleInject
    public ISubInstruction() {

    }

    public static ISubInstruction read(final DataInputStream in) {
        return new ISubInstruction();
    }
}
