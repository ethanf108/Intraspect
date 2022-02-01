package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x8A, mnemonic = "l2d")
public final class LongToDoubleInstruction extends ConversionInstruction {

    public LongToDoubleInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return long.class;
    }

    @Override
    public Class<?> getOutputType() {
        return double.class;
    }
}
