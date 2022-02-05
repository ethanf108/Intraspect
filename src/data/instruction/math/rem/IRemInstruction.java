package data.instruction.math.rem;

import data.instruction.Opcode;
import data.instruction.math.RemInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x70, mnemonic = "irem")
public final class IRemInstruction extends RemInstruction<Integer> {

    public IRemInstruction() {

    }

    public IRemInstruction read(final DataInputStream in) {
        return new IRemInstruction();
    }
}
