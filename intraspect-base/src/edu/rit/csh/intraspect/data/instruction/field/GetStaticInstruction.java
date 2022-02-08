package edu.rit.csh.intraspect.data.instruction.field;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB2, mnemonic = "getstatic")
public final class GetStaticInstruction extends FieldInstruction {

    public GetStaticInstruction(final int refIndex) {
        super(refIndex);
    }

    public static GetStaticInstruction read(final DataInputStream in) throws IOException {
        return new GetStaticInstruction(in.readUnsignedShort());
    }

}
