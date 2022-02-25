package edu.rit.csh.intraspect.data.instruction.invoke;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.InterfaceMethodRefConstant;
import edu.rit.csh.intraspect.data.constant.MethodRefConstant;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

import java.util.Optional;

public sealed abstract class InvokeInstruction extends Instruction permits InvokeVirtualInstruction, InvokeInterfaceInstruction, InvokeStaticInstruction, InvokeSpecialInstruction, InvokeDynamicInstruction {

    @ConstantPoolIndex({MethodRefConstant.class, InterfaceMethodRefConstant.class})
    protected final int methodIndex;

    @AssembleInject
    public InvokeInstruction(final int methodIndex) {
        this.methodIndex = methodIndex;
    }

    public int getMethodIndex() {
        return this.methodIndex;
    }

    public abstract Optional<? extends ConstantDesc> getMethodRef(final ClassFile ref);
}
