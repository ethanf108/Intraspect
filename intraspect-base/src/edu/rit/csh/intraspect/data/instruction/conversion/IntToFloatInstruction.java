package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x86, mnemonic = "i2f")
public final class IntToFloatInstruction extends ConversionInstruction {

    public IntToFloatInstruction() {

    }

    public static IntToFloatInstruction read(final DataInputStream in) throws IOException {
        return new IntToFloatInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return float.class;
    }

}
