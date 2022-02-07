package data.instruction.wide.load;

import data.ClassFile;

public final class WideILoadInstruction extends WideLoadInstruction {

    WideILoadInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideILoadInstruction(int localVariableIndex) {
        this(0x15, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x15;
    }

    @Override
    public String getSubMnemonic() {
        return "iload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return int.class;
    }

}
