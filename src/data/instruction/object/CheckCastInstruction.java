package data.instruction.object;

import data.instruction.ObjectInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC0, mnemonic = "checkcast")
public final class CheckCastInstruction extends ObjectInstruction {

    public CheckCastInstruction(int classIndex) {
        super(classIndex);
    }

    public static CheckCastInstruction read(DataInputStream in) throws IOException {
        return new CheckCastInstruction(in.readUnsignedShort());
    }
}
