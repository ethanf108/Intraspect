package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x85, mnemonic = "i2l")
public final class IntToLongInstruction extends ConversionInstruction {

    public IntToLongInstruction() {
    }

    public static IntToLongInstruction read(final DataInputStream in) throws IOException {
        return new IntToLongInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return long.class;
    }
}
