package data.instruction;

import data.ClassFile;
import data.ConstantDesc;
import data.constant.DoubleConstant;
import data.constant.EmptyWideConstant;
import data.constant.LongConstant;
import data.instruction.constant.Ldc2_wInstruction;
import data.instruction.constant.LdcInstruction;
import data.instruction.constant.Ldc_wInstruction;

import java.util.Optional;

public sealed abstract class LoadConstantInstruction extends Instruction permits LdcInstruction, Ldc_wInstruction, Ldc2_wInstruction {

    protected final int constantPoolIndex;

    public LoadConstantInstruction(int constantPoolIndex) {
        this.constantPoolIndex = constantPoolIndex;
    }

    public final int getConstantPoolIndex() {
        return this.constantPoolIndex;
    }

    @Override
    public boolean isValid(ClassFile ref) {
        //TODO Check Dynamic Constants for illegal Wides
        final ConstantDesc cd = ref.getConstantDesc(this.constantPoolIndex);
        return this.constantPoolIndex > 0 && !(cd instanceof LongConstant || cd instanceof DoubleConstant || cd instanceof EmptyWideConstant);
    }

    public final Optional<? extends ConstantDesc> getConstant(ClassFile ref) {
        if (this.constantPoolIndex == 0 || ref.getConstantDesc(this.constantPoolIndex) instanceof EmptyWideConstant) {
            return Optional.empty();
        }
        return Optional.ofNullable(ref.getConstantDesc(this.constantPoolIndex));
    }
}
