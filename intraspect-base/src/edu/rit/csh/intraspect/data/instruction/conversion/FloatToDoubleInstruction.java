package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8D, mnemonic = "f2d")
public final class FloatToDoubleInstruction extends ConversionInstruction {

    @AssembleInject
    public FloatToDoubleInstruction() {
    }

    public static FloatToDoubleInstruction read(final DataInputStream in) throws IOException {
        return new FloatToDoubleInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return float.class;
    }

    @Override
    public Class<?> getOutputType() {
        return double.class;
    }
}
