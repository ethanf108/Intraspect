package edu.rit.csh.intraspect.data.instruction.math.neg;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x74, mnemonic = "ineg")
public final class INegInstruction extends NegInstruction<Integer> {

    @AssembleInject
    public INegInstruction() {
    }

    public static INegInstruction read(final DataInputStream in) throws IOException {
        return new INegInstruction();
    }
}
