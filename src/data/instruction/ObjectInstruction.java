package data.instruction;

import data.ClassFile;
import data.constant.ClassConstant;
import data.instruction.object.ANewArrayInstruction;
import data.instruction.object.CheckCastInstruction;
import data.instruction.object.InstanceofInstruction;
import data.instruction.object.NewInstruction;

import java.util.Optional;

public sealed abstract class ObjectInstruction extends Instruction permits InstanceofInstruction, CheckCastInstruction, NewInstruction, ANewArrayInstruction {

    protected final int classIndex;

    protected ObjectInstruction(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    @Override
    public final int getNumOperands() {
        return 2;
    }

    @Override
    public final int[] getOperands() {
        return new int[]{(this.classIndex & 0xFF00) >> 8, this.classIndex & 0xFF};
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstantDesc(this.classIndex) instanceof ClassConstant;
    }

    public Optional<ClassConstant> getCastType(ClassFile ref) {
        if (this.classIndex == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(ref.getConstantDesc(this.classIndex) instanceof ClassConstant cc ? cc : null);
    }
}
