package edu.rit.csh.intraspect.data.instruction.field;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.constant.FieldRefConstant;
import edu.rit.csh.intraspect.data.instruction.Instruction;

public sealed abstract class FieldInstruction extends Instruction permits GetStaticInstruction, PutStaticInstruction, GetFieldInstruction, PutFieldInstruction {

    protected final int refIndex;

    public FieldInstruction(final int refIndex) {
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
    public final boolean isValid(final ClassFile ref) {
        return ref.getConstantDesc(this.refIndex) instanceof FieldRefConstant;
    }
}
