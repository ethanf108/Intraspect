package edu.rit.csh.intraspect.data.instruction.conversion;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;

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
