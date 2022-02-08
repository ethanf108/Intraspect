package edu.rit.csh.intraspect.data.instruction.branch;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.branch.Goto_wInstruction;
import edu.rit.csh.intraspect.data.instruction.branch.Jsr_wInstruction;

public sealed abstract class BranchWideInstruction extends Instruction permits Goto_wInstruction, Jsr_wInstruction {

    protected final long branchIndex;

    public BranchWideInstruction(final long branchIndex) {
        this.branchIndex = branchIndex;
    }

    public final long getBranchIndex() {
        return this.branchIndex;
    }

    @Override
    public final int getNumOperands() {
        return 4;
    }

    @Override
    public final int[] getOperands() {
        return new int[]{
                (int) (this.branchIndex & 0xFF000000) >> 24,
                (int) (this.branchIndex & 0xFF0000) >> 16,
                (int) (this.branchIndex & 0xFF00) >> 8,
                (int) this.branchIndex & 0xFF
        };
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }
}
