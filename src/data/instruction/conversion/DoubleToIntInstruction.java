package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x8E, mnemonic = "d2i")
public final class DoubleToIntInstruction extends ConversionInstruction {

    public DoubleToIntInstruction() {
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
