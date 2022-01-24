package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x8D, mnemonic = "f2d")
public final class FloatToDoubleInstruction extends ConversionInstruction {

    public FloatToDoubleInstruction() {
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