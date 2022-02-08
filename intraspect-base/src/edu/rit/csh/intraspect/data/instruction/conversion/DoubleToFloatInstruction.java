package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x90, mnemonic = "d2f")
public final class DoubleToFloatInstruction extends ConversionInstruction {

    public DoubleToFloatInstruction() {
    }

    public static DoubleToFloatInstruction read(final DataInputStream in) throws IOException {
        return new DoubleToFloatInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return double.class;
    }

    @Override
    public Class<?> getOutputType() {
        return float.class;
    }
}
