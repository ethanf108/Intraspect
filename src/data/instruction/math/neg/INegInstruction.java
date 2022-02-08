package data.instruction.math.neg;

import data.instruction.Opcode;
import data.instruction.math.NegInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x74, mnemonic = "ineg")
public final class INegInstruction extends NegInstruction<Integer> {

    public INegInstruction() {
    }

    public static INegInstruction read(final DataInputStream in) throws IOException {
        return new INegInstruction();
    }
}
