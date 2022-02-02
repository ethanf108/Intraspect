package data.instruction;

import data.ClassFile;
import data.instruction.reserved.BreakpointInstruction;
import data.instruction.reserved.ImpDep1Instruction;
import data.instruction.reserved.ImpDep2Instruction;

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
    public boolean isValid(ClassFile ref) {
        return false;
    }
}
