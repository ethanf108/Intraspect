package edu.rit.csh.intraspect.data.instruction.field;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB5, mnemonic = "putfield")
public final class PutFieldInstruction extends FieldInstruction {

    public PutFieldInstruction(final int refIndex) {
        super(refIndex);
    }

    public static PutFieldInstruction read(final DataInputStream in) throws IOException {
        return new PutFieldInstruction(in.readUnsignedShort());
    }

}
