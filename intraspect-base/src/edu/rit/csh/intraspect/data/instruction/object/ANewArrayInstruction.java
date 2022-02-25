package edu.rit.csh.intraspect.data.instruction.object;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xBD, mnemonic = "anewarray")
public final class ANewArrayInstruction extends ObjectInstruction {

    @AssembleInject
    public ANewArrayInstruction(final int classIndex) {
        super(classIndex);
    }

    public static ANewArrayInstruction read(final DataInputStream in) throws IOException {
        return new ANewArrayInstruction(in.readUnsignedShort());
    }
}
