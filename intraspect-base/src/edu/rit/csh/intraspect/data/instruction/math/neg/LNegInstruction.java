package edu.rit.csh.intraspect.data.instruction.math.neg;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x75, mnemonic = "lneg")
public final class LNegInstruction extends NegInstruction<Long> {

    public LNegInstruction() {
    }

    public static LNegInstruction read(final DataInputStream in) throws IOException {
        return new LNegInstruction();
    }

}
