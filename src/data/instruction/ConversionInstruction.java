package data.instruction;

import data.ClassFile;
import data.instruction.conversion.DoubleToFloatInstruction;
import data.instruction.conversion.DoubleToIntInstruction;
import data.instruction.conversion.DoubleToLongInstruction;
import data.instruction.conversion.FloatToDoubleInstruction;
import data.instruction.conversion.FloatToIntInstruction;
import data.instruction.conversion.FloatToLongInstruction;
import data.instruction.conversion.IntToByteInstruction;
import data.instruction.conversion.IntToCharInstruction;
import data.instruction.conversion.IntToDoubleInstruction;
import data.instruction.conversion.IntToFloatInstruction;
import data.instruction.conversion.IntToLongInstruction;
import data.instruction.conversion.IntToShortInstruction;
import data.instruction.conversion.LongToDoubleInstruction;
import data.instruction.conversion.LongToFloatInstruction;
import data.instruction.conversion.LongToIntInstruction;
import java.io.DataOutputStream;
import java.io.IOException;

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
    public final void write(DataOutputStream out) throws IOException {
        out.writeByte(this.getOpcode());
    }

    @Override
    public final boolean isValid(ClassFile ref) {
        return true;
    }

    @Override
    public String toString() {
        return this.getMnemonic();
    }
}
