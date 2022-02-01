package data.instruction.constant;

import data.ClassFile;
import data.instruction.ConstantInstruction;

public sealed abstract class LConstInstruction extends ConstantInstruction permits LConst_0Instruction, LConst_1Instruction {

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
        return long.class;
    }

    @Override
    public abstract Long getValue();
}
