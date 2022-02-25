package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8C, mnemonic = "f2l")
public final class FloatToLongInstruction extends ConversionInstruction {

    @AssembleInject
    public FloatToLongInstruction() {
    }

    public static FloatToLongInstruction read(final DataInputStream in) throws IOException {
        return new FloatToLongInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return float.class;
    }

    @Override
    public Class<?> getOutputType() {
        return long.class;
    }

}
