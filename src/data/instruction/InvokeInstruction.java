package data.instruction;

import data.ClassFile;
import data.ConstantDesc;
import data.instruction.invoke.*;

import java.util.Optional;

public sealed abstract class InvokeInstruction extends Instruction permits InvokeVirtualInstruction, InvokeInterfaceInstruction, InvokeStaticInstruction, InvokeSpecialInstruction, InvokeDynamicInstruction {

    protected final int methodIndex;

    protected InvokeInstruction(int methodIndex) {
        this.methodIndex = methodIndex;
    }

    public int getMethodIndex() {
        return this.methodIndex;
    }

    public abstract Optional<? extends ConstantDesc> getMethodRef(ClassFile ref);
}
