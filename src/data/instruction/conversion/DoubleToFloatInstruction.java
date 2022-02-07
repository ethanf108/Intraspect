package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x90, mnemonic = "d2f")
public final class DoubleToFloatInstruction extends ConversionInstruction {

    public DoubleToFloatInstruction() {
    }

    public static DoubleToFloatInstruction read(DataInputStream in) throws IOException {
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
