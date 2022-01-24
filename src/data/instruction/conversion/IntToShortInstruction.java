package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x93, mnemonic = "i2s")
public final class IntToShortInstruction extends ConversionInstruction {

    public IntToShortInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return short.class;
    }
}
