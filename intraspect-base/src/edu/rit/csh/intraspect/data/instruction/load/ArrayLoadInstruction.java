package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;

public sealed abstract class ArrayLoadInstruction extends Instruction permits
        IALoadInstruction, LALoadInstruction, FALoadInstruction, DALoadInstruction,
        AALoadInstruction, BALoadInstruction, CALoadInstruction, SALoadInstruction {

    protected ArrayLoadInstruction() {
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
        //TODO Maybe more checking?
        return true;
    }

    public abstract Class<?> getType();
}
