package data.instruction.constant;

import data.ClassFile;
import data.instruction.ConstantInstruction;

public abstract sealed class IConstInstruction extends ConstantInstruction permits IConst_M1Constant, IConst_0Instruction, IConst_1Instruction, IConst_2Instruction, IConst_3Instruction, IConst_4Instruction, IConst_5Instruction {

    @Override
    public final int getNumOperands() {
        return 0;
    }

    @Override
    public final int[] getOperands() {
        return new int[0];
    }

    @Override
    public final boolean isValid(final ClassFile ref) {
        return true;
    }

    @Override
    public final Class<?> getConstantType() {
        return int.class;
    }

    @Override
    public abstract Integer getValue();
}
