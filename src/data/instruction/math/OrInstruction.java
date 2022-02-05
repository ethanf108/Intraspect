package data.instruction.math;

import data.instruction.MathInstruction;
import data.instruction.math.or.IOrInstruction;
import data.instruction.math.or.LOrInstruction;

public sealed abstract class OrInstruction<T extends Number> extends MathInstruction<T> permits IOrInstruction, LOrInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() | b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() | b.longValue());

        throw new IllegalStateException();
    }
}
