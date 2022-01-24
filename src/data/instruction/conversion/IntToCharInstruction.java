package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x92, mnemonic = "i2c")
public final class IntToCharInstruction extends ConversionInstruction {

    public IntToCharInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return char.class;
    }
}
