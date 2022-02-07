package data.instruction.wide.load;

import data.ClassFile;

public final class WideALoadInstruction extends WideLoadInstruction {

    WideALoadInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideALoadInstruction(int localVariableIndex) {
        this(0x19, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x19;
    }

    @Override
    public String getSubMnemonic() {
        return "aload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return Object.class;
    }
}
