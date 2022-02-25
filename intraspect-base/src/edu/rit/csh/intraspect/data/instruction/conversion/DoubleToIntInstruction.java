package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8E, mnemonic = "d2i")
public final class DoubleToIntInstruction extends ConversionInstruction {

    @AssembleInject
    public DoubleToIntInstruction() {
    }

    public static DoubleToIntInstruction read(final DataInputStream in) throws IOException {
        return new DoubleToIntInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return double.class;
    }

    @Override
    public Class<?> getOutputType() {
        return int.class;
    }
}
