package data.instruction.misc;

import data.ClassFile;
import data.constant.ClassConstant;
import data.instruction.Instruction;
import data.instruction.Opcode;

import java.util.Optional;

@Opcode(opcode = 0xC1, mnemonic = "instanceof")
public final class InstanceofInstruction extends Instruction {

    private final int classIndex;

    public InstanceofInstruction(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getClassIndex() {
        return this.classIndex;
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
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
