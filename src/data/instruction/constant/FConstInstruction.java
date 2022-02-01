package data.instruction.constant;

import data.ClassFile;
import data.instruction.ConstantInstruction;

public sealed abstract class FConstInstruction extends ConstantInstruction permits FConst_0Instruction, FConst_1Instruction, FConst_2Instruction {

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
        return float.class;
    }

    @Override
    public abstract Float getValue();
}