package data.instruction;

import data.ClassFile;
import data.instruction.constant.AConstNullInstruction;
import data.instruction.constant.IConstInstruction;

public abstract sealed class ConstantInstruction extends Instruction permits AConstNullInstruction, IConstInstruction {

    public abstract Class<?> getConstantType();

    public abstract Object getValue();

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }
}
