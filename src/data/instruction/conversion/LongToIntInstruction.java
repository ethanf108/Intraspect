package data.instruction.conversion;

import data.instruction.ConversionInstruction;
import data.instruction.Opcode;

@Opcode(opcode = 0x88, mnemonic = "l2i")
public final class LongToIntInstruction extends ConversionInstruction {

    public LongToIntInstruction() {

    }

    @Override
    public Class<?> getInputType() {
        return long.class;
    }

    @Override
    public Class<?> getOutputType() {
        return int.class;
    }
}
