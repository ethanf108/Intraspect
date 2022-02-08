package data.instruction;

import data.ClassFile;
import data.instruction.compare.*;

import java.util.Comparator;

public sealed abstract class CompareInstruction<T extends Number> extends Instruction implements Comparator<T> permits LcmpInstruction, FcmplInstruction, FcmpgInstruction, DcmplInstruction, DcmpgInstruction {

    @Override
    public abstract int compare(final T a, final T b);

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }
}
