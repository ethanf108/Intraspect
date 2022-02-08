package data.instruction.monitor;

import data.instruction.MonitorInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC2, mnemonic = "monitorenter")
public final class MonitorEnterInstruction extends MonitorInstruction {

    public MonitorEnterInstruction() {

    }

    public static MonitorEnterInstruction read(final DataInputStream in) throws IOException {
        return new MonitorEnterInstruction();
    }
}
