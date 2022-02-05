package data.instruction.math.add;

import data.instruction.Opcode;
import data.instruction.math.AddInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x60, mnemonic = "iadd")
public final class IAddInstruction extends AddInstruction<Integer> {

    public IAddInstruction() {

    }

    public IAddInstruction read(final DataInputStream in) {
        return new IAddInstruction();
    }
}
