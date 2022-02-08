package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x8C, mnemonic = "f2l")
public final class FloatToLongInstruction extends ConversionInstruction {

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
