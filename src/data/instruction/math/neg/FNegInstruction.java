package data.instruction.math.neg;

import data.instruction.Opcode;
import data.instruction.math.NegInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x76, mnemonic = "fneg")
public final class FNegInstruction extends NegInstruction<Float> {

    public FNegInstruction() {
    }

    public static FNegInstruction read(DataInputStream in) throws IOException {
        return new FNegInstruction();
    }

}
