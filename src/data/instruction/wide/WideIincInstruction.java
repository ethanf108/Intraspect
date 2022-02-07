package data.instruction.wide;

import data.ClassFile;

public final class WideIincInstruction extends WideInstruction {

    private final int constValue;

    public WideIincInstruction(int subOpcode, int localVariableIndex, int constValue) {
        super(subOpcode, localVariableIndex);
        this.constValue = constValue;
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
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x84;
    }
}
