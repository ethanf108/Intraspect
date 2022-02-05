package data.instruction.math;

import data.instruction.MathInstruction;
import data.instruction.math.rem.DRemInstruction;
import data.instruction.math.rem.FRemInstruction;
import data.instruction.math.rem.IRemInstruction;
import data.instruction.math.rem.LRemInstruction;

public sealed abstract class RemInstruction<T extends Number> extends MathInstruction<T> permits DRemInstruction, FRemInstruction, IRemInstruction, LRemInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() % b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() % b.longValue());

        if (a instanceof Float)
            return (T) (Float) (a.floatValue() % b.floatValue());

        if (a instanceof Double)
            return (T) (Double) (a.doubleValue() % b.doubleValue());

        throw new IllegalStateException();
    }
}
