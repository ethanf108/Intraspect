package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;

import java.util.function.Predicate;

public sealed abstract class IfObjectInstruction extends BranchInstruction implements Predicate<Object> permits IfNullInstruction, IfNonNullInstruction {

    protected IfObjectInstruction(final int branchTarget) {
        super(branchTarget);
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public abstract boolean test(final Object value);

}
