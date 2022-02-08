package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.instruction.Instruction;

public sealed abstract class BranchInstruction extends Instruction permits IfInstruction, IfCompareInstruction, IfObjectInstruction, GotoInstruction, JsrInstruction {

    protected final int branchTarget;

    protected BranchInstruction(final int branchTarget) {
        this.branchTarget = branchTarget;
    }

    public int getBranchTarget() {
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
}
