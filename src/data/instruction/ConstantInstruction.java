package data.instruction;

import data.ClassFile;
import data.instruction.constant.*;

public abstract sealed class ConstantInstruction extends Instruction permits AConstNullInstruction, IConstInstruction, FConstInstruction, DConstInstruction, LConstInstruction {

    public abstract Class<?> getConstantType();

    public abstract Object getValue();

    @Override
    public boolean isValid(ClassFile ref) {
        return true;
    }
}
