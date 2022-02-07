package data.instruction.object;

import data.instruction.ObjectInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xC1, mnemonic = "instanceof")
public final class InstanceofInstruction extends ObjectInstruction {

    public InstanceofInstruction(int classIndex) {
        super(classIndex);
    }

    public static InstanceofInstruction read(DataInputStream in) throws IOException {
        return new InstanceofInstruction(in.readUnsignedShort());
    }
}
