package data.instruction;

import data.ClassFile;
import data.instruction.branch.*;

import java.util.function.IntPredicate;

public sealed abstract class IfInstruction extends Instruction implements IntPredicate permits IfeqInstruction, IfneInstruction, IfltInstruction, IfgeInstruction, IfgtInstruction, IfleInstruction {

    protected final int branchTarget;

    protected IfInstruction(int branchTarget) {
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
    public abstract boolean test(int value);
}
