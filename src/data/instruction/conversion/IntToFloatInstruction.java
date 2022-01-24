package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x86, mnemonic = "i2f")
public final class IntToFloatInstruction extends ConversionInstruction {

    public IntToFloatInstruction() {

    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return float.class;
    }

}
