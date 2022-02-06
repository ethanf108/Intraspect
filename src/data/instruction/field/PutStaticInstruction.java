package data.instruction.field;

import data.instruction.FieldInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB3, mnemonic = "putstatic")
public final class PutStaticInstruction extends FieldInstruction {

    public PutStaticInstruction(int refIndex) {
        super(refIndex);
    }

    public static PutStaticInstruction read(DataInputStream in) throws IOException {
        return new PutStaticInstruction(in.readUnsignedShort());
    }
}
