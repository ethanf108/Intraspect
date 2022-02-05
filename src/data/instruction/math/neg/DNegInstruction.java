package data.instruction.math.neg;

import data.instruction.Opcode;
import data.instruction.math.NegInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x77, mnemonic = "dneg")
public final class DNegInstruction extends NegInstruction<Double> {

    public DNegInstruction() {
    }

    public static DNegInstruction read(DataInputStream in) throws IOException {
        return new DNegInstruction();
    }

}
