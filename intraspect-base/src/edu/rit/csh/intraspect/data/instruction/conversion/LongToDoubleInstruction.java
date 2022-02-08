package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8A, mnemonic = "l2d")
public final class LongToDoubleInstruction extends ConversionInstruction {

    public LongToDoubleInstruction() {
    }

    public static LongToDoubleInstruction read(final DataInputStream in) throws IOException {
        return new LongToDoubleInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return long.class;
    }

    @Override
    public Class<?> getOutputType() {
        return double.class;
    }
}
