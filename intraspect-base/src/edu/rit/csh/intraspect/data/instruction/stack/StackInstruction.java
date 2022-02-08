package edu.rit.csh.intraspect.data.instruction.stack;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;

import java.util.Stack;

public sealed abstract class StackInstruction extends Instruction permits
        PopInstruction, Pop2Instruction, DupInstruction, Dup_x1Instruction,
        Dup_x2Instruction, Dup2Instruction, Dup2_x1Instruction, Dup2_x2Instruction,
        SwapInstruction {

    protected StackInstruction() {
    }

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return true;
    }

    public abstract <T> void apply(final Stack<T> stack);
}
