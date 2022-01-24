package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x8F, mnemonic = "d2l")
public final class DoubleToLongInstruction extends ConversionInstruction {

    public DoubleToLongInstruction() {
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
