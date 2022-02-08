package edu.rit.csh.intraspect.data.instruction.invoke;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.instruction.Instruction;

import java.util.Optional;

public sealed abstract class InvokeInstruction extends Instruction permits InvokeVirtualInstruction, InvokeInterfaceInstruction, InvokeStaticInstruction, InvokeSpecialInstruction, InvokeDynamicInstruction {

    protected final int methodIndex;

    protected InvokeInstruction(final int methodIndex) {
        this.methodIndex = methodIndex;
    }

    public int getMethodIndex() {
        return this.methodIndex;
    }

    public abstract Optional<? extends ConstantDesc> getMethodRef(final ClassFile ref);
}
