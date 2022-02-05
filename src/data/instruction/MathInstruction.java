package data.instruction;

import data.ClassFile;
import data.instruction.math.AddInstruction;
import data.instruction.math.OrInstruction;

import java.util.function.BinaryOperator;

public sealed abstract class MathInstruction<T extends Number> extends Instruction implements BinaryOperator<T> permits AddInstruction, data.instruction.math.AndInstruction, data.instruction.math.DivInstruction, data.instruction.math.MulInstruction, data.instruction.math.RemInstruction, data.instruction.math.ShlInstruction, data.instruction.math.ShrInstruction, data.instruction.math.SubInstruction, data.instruction.math.UshrInstruction, data.instruction.math.XorInstruction, OrInstruction {
    @Override
    public abstract T apply(final T a, final T b);

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
}
