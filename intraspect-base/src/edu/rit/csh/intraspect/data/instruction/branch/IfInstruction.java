package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;

import java.util.function.IntPredicate;

public sealed abstract class IfInstruction extends BranchInstruction implements IntPredicate permits IfeqInstruction, IfneInstruction, IfltInstruction, IfgeInstruction, IfgtInstruction, IfleInstruction {

    protected IfInstruction(final int branchTarget) {
        super(branchTarget);
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return false;
    }

    @Override
    public abstract boolean test(final int value);
}
