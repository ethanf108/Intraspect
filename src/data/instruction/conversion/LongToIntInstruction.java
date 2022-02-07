package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

import java.io.DataInputStream;
import java.io.IOException;

@Opcode(opcode = 0x88, mnemonic = "l2i")
public final class LongToIntInstruction extends ConversionInstruction {

    public LongToIntInstruction() {

    }

    public static LongToIntInstruction read(DataInputStream in) throws IOException {
        return new LongToIntInstruction();
    }

    @Override
    public Class<?> getInputType() {
        return long.class;
    }

    @Override
    public Class<?> getOutputType() {
        return int.class;
    }
}
