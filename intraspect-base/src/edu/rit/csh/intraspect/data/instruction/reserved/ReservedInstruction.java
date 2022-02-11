package edu.rit.csh.intraspect.data.instruction.reserved;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;

public sealed abstract class ReservedInstruction extends Instruction permits BreakpointInstruction, ImpDep1Instruction, ImpDep2Instruction {

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return false;
    }
}
