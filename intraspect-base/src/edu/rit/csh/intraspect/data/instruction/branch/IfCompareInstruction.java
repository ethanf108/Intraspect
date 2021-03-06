package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;

import java.util.function.BiPredicate;

public sealed abstract class IfCompareInstruction<T> extends BranchInstruction implements BiPredicate<T, T> permits
        If_icmpeqInstruction, If_icmpneInstruction, If_icmpltInstruction, If_icmpgeInstruction,
        If_icmpgtInstruction, If_icmpleInstruction, If_acmpeqInstruction, If_acmpneInstruction {

    protected IfCompareInstruction(final int branchTarget) {
        super(branchTarget);
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public abstract boolean test(final T a, final T b);

}
