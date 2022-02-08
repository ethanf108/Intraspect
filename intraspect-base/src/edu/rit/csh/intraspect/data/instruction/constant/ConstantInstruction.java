package edu.rit.csh.intraspect.data.instruction.constant;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;

public abstract sealed class ConstantInstruction extends Instruction permits
        AConstNullInstruction, IConstInstruction, FConstInstruction, DConstInstruction,
        LConstInstruction, BipushInstruction, SipushInstruction {

    public abstract Class<?> getConstantType();

    public abstract Object getValue();

    @Override
    public boolean isValid(final ClassFile ref) {
        return true;
    }
}
