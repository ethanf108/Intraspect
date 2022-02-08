package edu.rit.csh.intraspect.data.instruction.math.div;

import edu.rit.csh.intraspect.data.instruction.math.MathInstruction;

public sealed abstract class DivInstruction<T extends Number> extends MathInstruction<T> permits DDivInstruction, FDivInstruction, IDivInstruction, LDivInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() / b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() / b.longValue());

        if (a instanceof Float)
            return (T) (Float) (a.floatValue() / b.floatValue());

        if (a instanceof Double)
            return (T) (Double) (a.doubleValue() / b.doubleValue());

        throw new IllegalStateException();
    }
}
