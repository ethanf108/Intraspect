package data.instruction.math.sub;

import data.instruction.Opcode;
import data.instruction.math.SubInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x67, mnemonic = "dsub")
public final class DSubInstruction extends SubInstruction<Double> {

    public DSubInstruction() {

    }

    public DSubInstruction read(final DataInputStream in) {
        return new DSubInstruction();
    }
}
