package edu.rit.csh.intraspect.data.instruction.object;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ClassConstant;
import edu.rit.csh.intraspect.data.instruction.Instruction;

import java.util.Optional;

public sealed abstract class ObjectInstruction extends Instruction permits InstanceofInstruction, CheckCastInstruction, NewInstruction, ANewArrayInstruction {

    protected final int classIndex;

    protected ObjectInstruction(final int classIndex) {
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
    public boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.classIndex) instanceof ClassConstant;
    }

    public Optional<ClassConstant> getCastType(final ClassFile ref) {
        if (this.classIndex == 0) {
            return Optional.empty();
        }
        return Optional.ofNullable(ref.getConstantDesc(this.classIndex) instanceof ClassConstant cc ? cc : null);
    }
}
