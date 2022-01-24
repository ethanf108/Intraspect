package data.instruction.invoke;

import data.ClassFile;
import data.constant.DynamicConstant;
import data.instruction.InvokeInstruction;
import data.instruction.Opcode;
import java.util.Optional;

@Opcode(opcode = 0xBA, mnemonic = "invokedynamic")
public final class InvokeDynamicInstruction extends InvokeInstruction {

    public InvokeDynamicInstruction(int methodIndex) {
        super(methodIndex);
    }

    @Override
    public int getNumOperands() {
        return 4;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.methodIndex & 0xFF00) >> 8, this.methodIndex & 0xFF, 0, 0};
    }

    @Override
    public Optional<DynamicConstant> getMethodRef(ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.methodIndex) instanceof DynamicConstant dc ? dc : null);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstantDesc(this.methodIndex) instanceof DynamicConstant;
    }

}
