package data.instruction;

import data.ClassFile;
import data.instruction.branch.*;

import java.util.function.BiPredicate;

public sealed abstract class IfCompareInstruction<T> extends BranchInstruction implements BiPredicate<T, T> permits
        If_icmpeqInstruction, If_icmpneInstruction, If_icmpltInstruction, If_icmpgeInstruction,
        If_icmpgtInstruction, If_icmpleInstruction, If_acmpeqInstruction, If_acmpneInstruction {

    protected IfCompareInstruction(int branchTarget) {
        super(branchTarget);
    }

    @Override
    public final boolean isValid(ClassFile ref) {
        return false;
    }

    @Override
    public abstract boolean test(T a, T b);

}
