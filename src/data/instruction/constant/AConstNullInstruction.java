package data.instruction.constant;

import data.instruction.ConstantInstruction;

public final class AConstNullInstruction extends ConstantInstruction {
    @Override
    public Class<?> getConstantType() {
        return Object.class;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }
}
