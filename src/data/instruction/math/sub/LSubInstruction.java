package data.instruction.math.sub;

import data.instruction.Opcode;
import data.instruction.math.SubInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x65, mnemonic = "lsub")
public final class LSubInstruction extends SubInstruction<Long> {

    public LSubInstruction() {

    }

    public LSubInstruction read(final DataInputStream in) {
        return new LSubInstruction();
    }
}
