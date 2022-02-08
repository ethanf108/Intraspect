package edu.rit.csh.intraspect.data.instruction.object;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xBB, mnemonic = "new")
public final class NewInstruction extends ObjectInstruction {

    public NewInstruction(final int classIndex) {
        super(classIndex);
    }

    public static NewInstruction read(final DataInputStream in) throws IOException {
        return new NewInstruction(in.readUnsignedShort());
    }
}
