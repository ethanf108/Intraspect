package edu.rit.csh.intraspect.data.instruction.wide;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class WideIincInstruction extends WideInstruction {

    private final int constValue;

    public WideIincInstruction(final int subOpcode, final int localVariableIndex, final int constValue) {
        super(subOpcode, localVariableIndex);
        this.constValue = constValue;
    }

    @AssembleInject
    public WideIincInstruction(final int localVariableIndex, final int constValue) {
        this(0x84, localVariableIndex, constValue);
    }

    public int getConstValue() {
        return this.constValue;
    }

    @Override
    public String getSubMnemonic() {
        return "iinc_w";
    }

    @Override
    public int getNumOperands() {
        return 5;
    }

    @Override
    public int[] getOperands() {
        return new int[]{this.subOpcode & 0xFF, (this.localVariableIndex & 0xFF00) >> 8, this.localVariableIndex & 0xFF, (this.constValue & 0xFF00) >> 8, this.constValue & 0xFF};
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x84;
    }
}
