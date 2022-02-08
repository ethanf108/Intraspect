package data.instruction;

import data.ClassFile;
import data.instruction.conversion.*;

public sealed abstract class ConversionInstruction extends Instruction permits
        IntToLongInstruction, IntToFloatInstruction, IntToDoubleInstruction,
        LongToIntInstruction, LongToFloatInstruction, LongToDoubleInstruction,
        FloatToIntInstruction, FloatToLongInstruction, FloatToDoubleInstruction,
        DoubleToIntInstruction, DoubleToLongInstruction, DoubleToFloatInstruction,
        IntToByteInstruction, IntToCharInstruction, IntToShortInstruction {

    public abstract Class<?> getInputType();

    public abstract Class<?> getOutputType();

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public String toString() {
        return this.getMnemonic();
    }
}
