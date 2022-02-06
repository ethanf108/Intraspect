package data.instruction;

import data.ClassFile;
import data.instruction.branch.*;

import java.util.function.IntPredicate;

public sealed abstract class IfInstruction extends BranchInstruction implements IntPredicate permits IfeqInstruction, IfneInstruction, IfltInstruction, IfgeInstruction, IfgtInstruction, IfleInstruction {

    protected IfInstruction(int branchTarget) {
        super(branchTarget);
    }

    @Override
    public final boolean isValid(ClassFile ref) {
        return false;
    }

    @Override
    public abstract boolean test(int value);
}
