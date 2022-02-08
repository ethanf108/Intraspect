package edu.rit.csh.intraspect.data.instruction.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.ConstantDesc;
import edu.rit.csh.intraspect.data.constant.DoubleConstant;
import edu.rit.csh.intraspect.data.constant.EmptyWideConstant;
import edu.rit.csh.intraspect.data.constant.LongConstant;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.data.instruction.constant.Ldc2_wInstruction;
import edu.rit.csh.intraspect.data.instruction.constant.LdcInstruction;
import edu.rit.csh.intraspect.data.instruction.constant.Ldc_wInstruction;

import java.util.Optional;

public sealed abstract class LoadConstantInstruction extends Instruction permits LdcInstruction, Ldc_wInstruction, Ldc2_wInstruction {

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
