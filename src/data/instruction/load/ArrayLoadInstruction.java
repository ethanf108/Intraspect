package data.instruction.load;

import data.ClassFile;
import data.instruction.Instruction;
import data.instruction.t.*;

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
    public boolean isValid(ClassFile ref) {
        //TODO Maybe more checking?
        return true;
    }

    public abstract Class<?> getType();
}
