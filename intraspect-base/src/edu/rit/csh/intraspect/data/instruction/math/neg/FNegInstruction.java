package edu.rit.csh.intraspect.data.instruction.math.neg;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x76, mnemonic = "fneg")
public final class FNegInstruction extends NegInstruction<Float> {

    @AssembleInject
    public FNegInstruction() {
    }

    public static FNegInstruction read(final DataInputStream in) throws IOException {
        return new FNegInstruction();
    }

}
