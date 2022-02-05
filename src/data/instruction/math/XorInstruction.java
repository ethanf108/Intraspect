package data.instruction.math;

import data.instruction.MathInstruction;
import data.instruction.math.xor.IXorInstruction;
import data.instruction.math.xor.LXorInstruction;

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
