package edu.rit.csh.intraspect.data.instruction.math.neg;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;

import java.util.function.UnaryOperator;

public sealed abstract class NegInstruction<T extends Number> extends Instruction implements UnaryOperator<T> permits INegInstruction, LNegInstruction, FNegInstruction, DNegInstruction {

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

    @SuppressWarnings("unchecked")
    @Override
    public final T apply(T value) {
        if (value instanceof Integer) {
            return (T) (Integer) (-value.intValue());
        } else if (value instanceof Long) {
            return (T) (Long) (-value.longValue());
        } else if (value instanceof Float) {
            return (T) (Float) (-value.floatValue());
        } else if (value instanceof Double) {
            return (T) (Double) (-value.doubleValue());
        }
        throw new IllegalStateException();
    }
}
