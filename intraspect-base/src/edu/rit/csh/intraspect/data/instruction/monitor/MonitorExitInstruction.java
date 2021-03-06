package edu.rit.csh.intraspect.data.instruction.monitor;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC3, mnemonic = "monitorexit")
public final class MonitorExitInstruction extends MonitorInstruction {

    public MonitorExitInstruction() {

    }

    public static MonitorExitInstruction read(final DataInputStream in) throws IOException {
        return new MonitorExitInstruction();
    }
}
