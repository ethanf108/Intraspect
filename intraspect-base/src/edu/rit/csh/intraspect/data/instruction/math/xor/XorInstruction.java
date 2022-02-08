package edu.rit.csh.intraspect.data.instruction.math.xor;

import edu.rit.csh.intraspect.data.instruction.math.MathInstruction;

public sealed abstract class XorInstruction<T extends Number> extends MathInstruction<T> permits IXorInstruction, LXorInstruction {

    @Override
    @SuppressWarnings("unchecked")
    public final T apply(final T a, final T b) {

        if (a instanceof Integer)
            return (T) (Integer) (a.intValue() ^ b.intValue());

        if (a instanceof Long)
            return (T) (Long) (a.longValue() ^ b.longValue());

        throw new IllegalStateException();
    }
}
