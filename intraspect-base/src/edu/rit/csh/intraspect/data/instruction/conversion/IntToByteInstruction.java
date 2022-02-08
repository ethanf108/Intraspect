package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x91, mnemonic = "i2b")
public final class IntToByteInstruction extends ConversionInstruction {

    public IntToByteInstruction() {
    }

    public static IntToByteInstruction read(final DataInputStream in) throws IOException {
        return new IntToByteInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return byte.class;
    }
}
