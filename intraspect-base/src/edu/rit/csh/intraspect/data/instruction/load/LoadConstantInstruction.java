package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.*;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.constant.Ldc2_wInstruction;
import edu.rit.csh.intraspect.data.instruction.constant.LdcInstruction;
import edu.rit.csh.intraspect.data.instruction.constant.Ldc_wInstruction;
import edu.rit.csh.intraspect.edit.ConstantPoolIndex;

import java.util.Optional;

public sealed abstract class LoadConstantInstruction extends Instruction permits LdcInstruction, Ldc_wInstruction, Ldc2_wInstruction {

    @ConstantPoolIndex({
            IntegerConstant.class,
            FloatConstant.class,
            LongConstant.class,
            DoubleConstant.class,
            ClassConstant.class,
            StringConstant.class,
            MethodHandleConstant.class,
            MethodTypeConstant.class,
            DynamicConstant.class
    })
    protected final int constantPoolIndex;

    public LoadConstantInstruction(final int constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }

    public final int getConstantPoolIndex() {
        return this.constantPoolIndex;
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        //TODO Check Dynamic Constants for illegal Wides
        final ConstantDesc cd = ref.getConstantDesc(this.constantPoolIndex);
        return this.constantPoolIndex > 0 && !(cd instanceof LongConstant || cd instanceof DoubleConstant || cd instanceof EmptyWideConstant);
    }

    public final Optional<? extends ConstantDesc> getConstant(final ClassFile ref) {
        if (this.constantPoolIndex == 0 || ref.getConstantDesc(this.constantPoolIndex) instanceof EmptyWideConstant) {
            return Optional.empty();
        }
        return Optional.ofNullable(ref.getConstantDesc(this.constantPoolIndex));
    }
}
