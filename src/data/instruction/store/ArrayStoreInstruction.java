package data.instruction.store;

import data.ClassFile;
import data.instruction.Instruction;

public sealed abstract class ArrayStoreInstruction extends Instruction permits
        IAStoreInstruction, LAStoreInstruction, FAStoreInstruction, DAStoreInstruction,
        AAStoreInstruction, BAStoreInstruction, CAStoreInstruction, SAStoreInstruction {

    protected ArrayStoreInstruction() {
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
