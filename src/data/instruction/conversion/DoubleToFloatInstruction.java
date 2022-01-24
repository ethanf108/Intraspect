package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x90, mnemonic = "d2f")
public final class DoubleToFloatInstruction extends ConversionInstruction {

    public DoubleToFloatInstruction() {
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
