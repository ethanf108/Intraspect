package data.instruction;

import data.ClassFile;
import data.instruction.load.*;

public sealed abstract class LoadInstruction extends Instruction permits ILoadInstruction, FLoadInstruction, DLoadInstruction, LLoadInstruction, ALoadInstruction {

    protected final int localVariableIndex;

    protected LoadInstruction(int lvi) {
        this.localVariableIndex = lvi;
    }

    public final int getLocalVariableIndex() {
        return this.localVariableIndex;
    }

    @Override
    public int getNumOperands() {
        return 1;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.localVariableIndex & 0xFF};
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO implements local variable index checking
        return true;
    }

    public abstract Class<?> getType();
}
