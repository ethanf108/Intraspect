package edu.rit.csh.intraspect.data.instruction.monitor;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.monitor.MonitorEnterInstruction;
import edu.rit.csh.intraspect.data.instruction.monitor.MonitorExitInstruction;

public sealed abstract class MonitorInstruction extends Instruction permits MonitorEnterInstruction, MonitorExitInstruction {

    protected MonitorInstruction() {

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
}
