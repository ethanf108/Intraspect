package data.instruction.math;

import data.instruction.MathInstruction;

public sealed abstract class AddInstruction<T extends Number> extends MathInstruction<T> permits IAdd {

    @Override
    @SuppressWarnings("unchecked")
    public T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() + b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() + b.longValue());

        if (a instanceof Float)
            return (T) (Float) (a.floatValue() + b.floatValue());

        if (a instanceof Double)
            return (T) (Double) (a.doubleValue() + b.doubleValue());

        throw new IllegalStateException();
    }
}
