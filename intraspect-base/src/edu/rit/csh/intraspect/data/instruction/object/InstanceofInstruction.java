package edu.rit.csh.intraspect.data.instruction.object;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC1, mnemonic = "instanceof")
public final class InstanceofInstruction extends ObjectInstruction {

    @AssembleInject
    public InstanceofInstruction(final int classIndex) {
        super(classIndex);
    }

    public static InstanceofInstruction read(final DataInputStream in) throws IOException {
        return new InstanceofInstruction(in.readUnsignedShort());
    }
}
