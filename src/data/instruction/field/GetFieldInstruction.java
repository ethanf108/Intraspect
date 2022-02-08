package data.instruction.field;

import data.instruction.FieldInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB4, mnemonic = "getfield")
public final class GetFieldInstruction extends FieldInstruction {

    public GetFieldInstruction(final int refIndex) {
        super(refIndex);
    }

    public static GetFieldInstruction read(final DataInputStream in) throws IOException {
        return new GetFieldInstruction(in.readUnsignedShort());
    }

}
