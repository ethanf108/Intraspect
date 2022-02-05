package data.instruction.math;

import data.instruction.MathInstruction;
import data.instruction.math.sub.DSubInstruction;
import data.instruction.math.sub.FSubInstruction;
import data.instruction.math.sub.ISubInstruction;
import data.instruction.math.sub.LSubInstruction;

public sealed abstract class SubInstruction<T extends Number> extends MathInstruction<T> permits DSubInstruction, FSubInstruction, ISubInstruction, LSubInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() - b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() - b.longValue());

        if (a instanceof Float)
            return (T) (Float) (a.floatValue() - b.floatValue());

        if (a instanceof Double)
            return (T) (Double) (a.doubleValue() - b.doubleValue());

        throw new IllegalStateException();
    }
}
