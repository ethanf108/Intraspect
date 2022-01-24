package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x8C, mnemonic = "f2l")
public final class FloatToLongInstruction extends ConversionInstruction {

    public FloatToLongInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return float.class;
    }

    @Override
    public Class<?> getOutputType() {
        return long.class;
    }

}
