package edu.rit.csh.intraspect.data.instruction.math.and;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x7e, mnemonic = "iand")
public final class IAndInstruction extends AndInstruction<Integer> {

    @AssembleInject
    public IAndInstruction() {

    }

    public static IAndInstruction read(final DataInputStream in) {
        return new IAndInstruction();
    }
}
