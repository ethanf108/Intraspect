package data.instruction;

import data.ClassFile;
import data.instruction.branch.IfNonNullInstruction;
import data.instruction.branch.IfNullInstruction;

import java.util.function.Predicate;

public sealed abstract class IfObjectInstruction extends Instruction implements Predicate<Object> permits IfNullInstruction, IfNonNullInstruction {

    protected final int branchTarget;

    protected IfObjectInstruction(int branchTarget) {
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
    public abstract boolean test(Object value);

}
