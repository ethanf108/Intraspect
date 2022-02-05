package data.instruction.math.sub;

import data.instruction.Opcode;
import data.instruction.math.SubInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x66, mnemonic = "fsub")
public final class FSubInstruction extends SubInstruction<Float> {

    public FSubInstruction() {

    }

    public FSubInstruction read(final DataInputStream in) {
        return new FSubInstruction();
    }
}
