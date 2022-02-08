package data.instruction.object;

import data.instruction.ObjectInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xBD, mnemonic = "anewarray")
public final class ANewArrayInstruction extends ObjectInstruction {

    public ANewArrayInstruction(final int classIndex) {
        super(classIndex);
    }

    public static ANewArrayInstruction read(final DataInputStream in) throws IOException {
        return new ANewArrayInstruction(in.readUnsignedShort());
    }
}
