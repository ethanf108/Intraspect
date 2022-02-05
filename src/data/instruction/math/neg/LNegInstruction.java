package data.instruction.math.neg;

import data.instruction.Opcode;
import data.instruction.math.NegInstruction;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x75, mnemonic = "lneg")
public final class LNegInstruction extends NegInstruction<Long> {

    public LNegInstruction() {
    }

    public static LNegInstruction read(DataInputStream in) throws IOException {
        return new LNegInstruction();
    }

}
