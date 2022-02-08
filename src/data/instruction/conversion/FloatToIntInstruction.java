package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8B, mnemonic = "f2i")
public final class FloatToIntInstruction extends ConversionInstruction {

    public FloatToIntInstruction() {
    }

    public static FloatToIntInstruction read(final DataInputStream in) throws IOException {
        return new FloatToIntInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return float.class;
    }

    @Override
    public Class<?> getOutputType() {
        return int.class;
    }
}
