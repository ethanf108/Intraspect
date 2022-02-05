package data.instruction.math.sub;

import data.instruction.Opcode;
import data.instruction.math.SubInstruction;

import java.io.DataInputStream;

@Opcode(opcode = 0x64, mnemonic = "isub")
public final class ISubInstruction extends SubInstruction<Integer> {

    public ISubInstruction() {

    }

    public ISubInstruction read(final DataInputStream in) {
        return new ISubInstruction();
    }
}
