package data.instruction.math.div;

import data.instruction.Opcode;
import data.instruction.math.DivInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x6d, mnemonic = "ldiv")
public final class LDivInstruction extends DivInstruction<Long> {

    public LDivInstruction() {

    }

    public LDivInstruction read(final DataInputStream in) {
        return new LDivInstruction();
    }
}
