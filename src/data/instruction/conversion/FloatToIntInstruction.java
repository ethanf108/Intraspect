package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x8B, mnemonic = "f2i")
public final class FloatToIntInstruction extends ConversionInstruction {

    public FloatToIntInstruction() {
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
