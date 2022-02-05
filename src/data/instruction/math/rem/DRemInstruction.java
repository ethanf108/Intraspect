package data.instruction.math.rem;

import data.instruction.Opcode;
import data.instruction.math.RemInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x73, mnemonic = "drem")
public final class DRemInstruction extends RemInstruction<Double> {

    public DRemInstruction() {

    }

    public DRemInstruction read(final DataInputStream in) {
        return new DRemInstruction();
    }
}
