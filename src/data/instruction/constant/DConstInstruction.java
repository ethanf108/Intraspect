package data.instruction.constant;

import data.ClassFile;
import data.instruction.ConstantInstruction;

public sealed abstract class DConstInstruction extends ConstantInstruction permits DConst_0Instruction, DConst_1Instruction {

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
        return true;
    }

    @Override
    public final Class<?> getConstantType() {
        return double.class;
    }

    @Override
    public abstract Double getValue();
}