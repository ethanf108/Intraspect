package edu.rit.csh.intraspect.data.instruction.math.add;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;

@Opcode(opcode = 0x60, mnemonic = "iadd")
public final class IAddInstruction extends AddInstruction<Integer> {

    @AssembleInject
    public IAddInstruction() {

    }

    public static IAddInstruction read(final DataInputStream in) {
        return new IAddInstruction();
    }
}
