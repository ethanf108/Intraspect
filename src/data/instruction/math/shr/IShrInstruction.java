package data.instruction.math.shr;

import data.instruction.Opcode;
import data.instruction.math.ShrInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x7a, mnemonic = "ishr")
public final class IShrInstruction extends ShrInstruction<Integer> {

    public IShrInstruction() {

    }

    public IShrInstruction read(final DataInputStream in) {
        return new IShrInstruction();
    }
}
