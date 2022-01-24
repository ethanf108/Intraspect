package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x85, mnemonic = "i2l")
public final class IntToLongInstruction extends ConversionInstruction {

    public IntToLongInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return long.class;
    }
}
