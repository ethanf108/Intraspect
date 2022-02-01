package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x93, mnemonic = "i2s")
public final class IntToShortInstruction extends ConversionInstruction {

    public IntToShortInstruction() {
    }

    public static IntToShortInstruction read(DataInputStream in) throws IOException {
        return new IntToShortInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return short.class;
    }
}
