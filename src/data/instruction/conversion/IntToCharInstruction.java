package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x92, mnemonic = "i2c")
public final class IntToCharInstruction extends ConversionInstruction {

    public IntToCharInstruction() {
    }

    public static IntToCharInstruction read(final DataInputStream in) throws IOException {
        return new IntToCharInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return char.class;
    }
}
