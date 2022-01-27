package data.instruction.invoke;

import data.ClassFile;
import data.constant.MethodRefConstant;
import data.instruction.InvokeInstruction;
import data.instruction.Opcode;
import java.util.Optional;

@Opcode(opcode = 0xB7, mnemonic = "invokespecial")
public final class InvokeSpecialInstruction extends InvokeInstruction {

    public InvokeSpecialInstruction(int methodIndex) {
        super(methodIndex);
    }

    @Override
    public int getNumOperands() {
        return 2;
    }

    @Override
    public int[] getOperands() {
        return new int[]{(this.methodIndex & 0xFF00) >> 8, this.methodIndex & 0xFF};
    }

    @Override
    public Optional<MethodRefConstant> getMethodRef(ClassFile ref) {
        return Optional.ofNullable(ref.getConstantDesc(this.methodIndex) instanceof MethodRefConstant mrc ? mrc : null);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return ref.getConstantDesc(this.methodIndex) instanceof MethodRefConstant;
    }

}