package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x89, mnemonic = "l2f")
public final class LongToFloatInstruction extends ConversionInstruction {

    public LongToFloatInstruction() {
    }

    public static LongToFloatInstruction read(final DataInputStream in) throws IOException {
        return new LongToFloatInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return long.class;
    }

    @Override
    public Class<?> getOutputType() {
        return float.class;
    }
}
