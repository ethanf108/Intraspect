package data.instruction;

import data.ClassFile;
import data.instruction.branch.IfNonNullInstruction;
import data.instruction.branch.IfNullInstruction;

import java.util.function.Predicate;

public sealed abstract class IfObjectInstruction extends BranchInstruction implements Predicate<Object> permits IfNullInstruction, IfNonNullInstruction {

    protected IfObjectInstruction(final int branchTarget) {
        super(branchTarget);
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return false;
    }

    @Override
    public abstract boolean test(final Object value);

}
