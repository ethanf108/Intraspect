package edu.rit.csh.intraspect.data.instruction.math.or;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x80, mnemonic = "ior")
public final class IOrInstruction extends OrInstruction<Integer> {

    @AssembleInject
    public IOrInstruction() {

    }

    public static IOrInstruction read(final DataInputStream in) {
        return new IOrInstruction();
    }
}
