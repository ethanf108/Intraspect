package data.instruction.field;

import data.instruction.FieldInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB2, mnemonic = "getstatic")
public final class GetStaticInstruction extends FieldInstruction {

    public GetStaticInstruction(int refIndex) {
        super(refIndex);
    }

    public static GetStaticInstruction read(DataInputStream in) throws IOException {
        return new GetStaticInstruction(in.readUnsignedShort());
    }

}
