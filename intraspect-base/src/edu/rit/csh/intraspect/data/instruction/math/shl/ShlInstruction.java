package edu.rit.csh.intraspect.data.instruction.math.shl;

import edu.rit.csh.intraspect.data.instruction.math.MathInstruction;

public sealed abstract class ShlInstruction<T extends Number> extends MathInstruction<T> permits IShlInstruction, LShlInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() << b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() << b.longValue());

        throw new IllegalStateException();
    }
}
