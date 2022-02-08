package edu.rit.csh.intraspect.data.instruction.reserved;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xCA, mnemonic = "breakpoint")
public final class BreakpointInstruction extends ReservedInstruction {

    public BreakpointInstruction() {

    }

    public static BreakpointInstruction read(final DataInputStream in) throws IOException {
        return new BreakpointInstruction();
    }
}
