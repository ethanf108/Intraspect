package data.instruction;

import data.ClassFile;
import data.instruction.monitor.MonitorEnterInstruction;
import data.instruction.monitor.MonitorExitInstruction;

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
