package data.instruction;

import data.ClassFile;
import data.instruction.math.*;

import java.util.function.BinaryOperator;

public sealed abstract class MathInstruction<T extends Number> extends Instruction implements BinaryOperator<T> permits AddInstruction, AndInstruction, DivInstruction, MulInstruction, RemInstruction, ShlInstruction, ShrInstruction, SubInstruction, UshrInstruction, XorInstruction, OrInstruction {
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
