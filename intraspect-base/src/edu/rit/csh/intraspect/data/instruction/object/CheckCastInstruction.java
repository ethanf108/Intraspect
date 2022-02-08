package edu.rit.csh.intraspect.data.instruction.object;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC0, mnemonic = "checkcast")
public final class CheckCastInstruction extends ObjectInstruction {

    public CheckCastInstruction(final int classIndex) {
        super(classIndex);
    }

    public static CheckCastInstruction read(final DataInputStream in) throws IOException {
        return new CheckCastInstruction(in.readUnsignedShort());
    }
}
