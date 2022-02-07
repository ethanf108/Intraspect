package data.instruction.wide.load;

import data.ClassFile;

public final class WideFLoadInstruction extends WideLoadInstruction {

    WideFLoadInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideFLoadInstruction(int localVariableIndex) {
        this(0x17, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x17;
    }

    @Override
    public String getSubMnemonic() {
        return "fload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return float.class;
    }

}
