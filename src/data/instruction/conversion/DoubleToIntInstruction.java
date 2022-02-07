package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8E, mnemonic = "d2i")
public final class DoubleToIntInstruction extends ConversionInstruction {

    public DoubleToIntInstruction() {
    }

    public static DoubleToIntInstruction read(DataInputStream in) throws IOException {
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
