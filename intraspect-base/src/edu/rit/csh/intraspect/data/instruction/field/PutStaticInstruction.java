package edu.rit.csh.intraspect.data.instruction.field;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0xB3, mnemonic = "putstatic")
public final class PutStaticInstruction extends FieldInstruction {

    public PutStaticInstruction(final int refIndex) {
        super(refIndex);
    }

    public static PutStaticInstruction read(final DataInputStream in) throws IOException {
        return new PutStaticInstruction(in.readUnsignedShort());
    }
}
