package data.instruction.reserved;

import data.instruction.Opcode;
import data.instruction.ReservedInstruction;

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
