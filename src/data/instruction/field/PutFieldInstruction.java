package data.instruction.field;

import data.instruction.FieldInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB5, mnemonic = "putfield")
public final class PutFieldInstruction extends FieldInstruction {

    public PutFieldInstruction(int refIndex) {
        super(refIndex);
    }

    public static PutFieldInstruction read(DataInputStream in) throws IOException {
        return new PutFieldInstruction(in.readUnsignedShort());
    }

}
