package data.instruction;

import data.ClassFile;
import data.instruction.branch.*;

import java.util.function.BiPredicate;

public sealed abstract class IfCompareInstruction<T> extends Instruction implements BiPredicate<T, T> permits
        If_icmpeqInstruction, If_icmpneInstruction, If_icmpltInstruction, If_icmpgeInstruction,
        If_icmpgtInstruction, If_icmpleInstruction, If_acmpeqInstruction, If_acmpneInstruction {

    protected final int branchTarget;

    protected IfCompareInstruction(int branchTarget) {
        this.branchTarget = branchTarget;
    }

    public final int getBranchTarget() {
        return this.branchTarget;
    }

    @Override
    public final int getNumOperands() {
        return 2;
    }

    @Override
    public final int[] getOperands() {
        return new int[]{(this.branchTarget & 0xFF00) >> 8, this.branchTarget & 0xFF};
    }

    @Override
    public final boolean isValid(ClassFile ref) {
        return false;
    }

    @Override
    public abstract boolean test(T a, T b);

}
