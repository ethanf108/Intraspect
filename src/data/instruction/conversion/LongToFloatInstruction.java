package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x89, mnemonic = "l2f")
public final class LongToFloatInstruction extends ConversionInstruction {

    public LongToFloatInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return long.class;
    }

    @Override
    public Class<?> getOutputType() {
        return float.class;
    }
}
