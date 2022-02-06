package data.instruction;

import data.ClassFile;
import data.constant.FieldRefConstant;
import data.instruction.field.GetFieldInstruction;
import data.instruction.field.GetStaticInstruction;
import data.instruction.field.PutFieldInstruction;
import data.instruction.field.PutStaticInstruction;

public sealed abstract class FieldInstruction extends Instruction permits GetStaticInstruction, PutStaticInstruction, GetFieldInstruction, PutFieldInstruction {

    protected final int refIndex;

    public FieldInstruction(int refIndex) {
        this.refIndex = refIndex;
    }

    public int getRefIndex() {
        return this.refIndex;
    }

    @Override
    public final int getNumOperands() {
        return 2;
    }

    @Override
    public final int[] getOperands() {
        return new int[]{(this.refIndex & 0xFF00) >> 8, this.refIndex & 0xFF};
    }

    @Override
    public final boolean isValid(ClassFile ref) {
        return ref.getConstantDesc(this.refIndex) instanceof FieldRefConstant;
    }
}
