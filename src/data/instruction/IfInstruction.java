package data.instruction;

import data.ClassFile;
import data.instruction.branch.*;

import java.util.function.IntPredicate;

public sealed abstract class IfInstruction extends Instruction implements IntPredicate permits IfeqInstruction, IfneInstruction, IfltInstruction, IfgeInstruction, IfgtInstruction, IfleInstruction {

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public final boolean isValid(ClassFile ref) {
        return false;
    }

    @Override
    public abstract boolean test(int value);
}
