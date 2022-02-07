package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8D, mnemonic = "f2d")
public final class FloatToDoubleInstruction extends ConversionInstruction {

    public FloatToDoubleInstruction() {
    }

    public static FloatToDoubleInstruction read(DataInputStream in) throws IOException {
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
