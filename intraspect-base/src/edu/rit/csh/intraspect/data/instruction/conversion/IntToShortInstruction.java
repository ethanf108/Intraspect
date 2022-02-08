package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x93, mnemonic = "i2s")
public final class IntToShortInstruction extends ConversionInstruction {

    public IntToShortInstruction() {
    }

    public static IntToShortInstruction read(final DataInputStream in) throws IOException {
        return new IntToShortInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return short.class;
    }
}
