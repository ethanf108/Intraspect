package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x91, mnemonic = "i2b")
public final class IntToByteInstruction extends ConversionInstruction {

    public IntToByteInstruction() {
    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return byte.class;
    }
}
