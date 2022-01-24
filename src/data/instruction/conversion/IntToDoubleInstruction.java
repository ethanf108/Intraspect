package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x87, mnemonic = "i2d")
public final class IntToDoubleInstruction extends ConversionInstruction {

    public IntToDoubleInstruction() {

    }

    @Override
    public Class<?> getInputType() {
        return int.class;
    }

    @Override
    public Class<?> getOutputType() {
        return double.class;
    }

}
