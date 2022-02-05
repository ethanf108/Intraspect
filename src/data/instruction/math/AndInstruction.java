package data.instruction.math;

import data.instruction.MathInstruction;
import data.instruction.math.and.IAndInstruction;
import data.instruction.math.and.LAndInstruction;

public sealed abstract class AndInstruction<T extends Number> extends MathInstruction<T> permits IAndInstruction, LAndInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() & b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() & b.longValue());

        throw new IllegalStateException();
    }
}
