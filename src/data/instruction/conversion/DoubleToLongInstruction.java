package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8F, mnemonic = "d2l")
public final class DoubleToLongInstruction extends ConversionInstruction {

    public DoubleToLongInstruction() {
    }

    public static DoubleToLongInstruction read(final DataInputStream in) throws IOException {
        return new DoubleToLongInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return double.class;
    }

    @Override
    public Class<?> getOutputType() {
        return long.class;
    }
}
