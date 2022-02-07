package data.instruction.wide.load;

import data.ClassFile;

public final class WideLLoadInstruction extends WideLoadInstruction {

    WideLLoadInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideLLoadInstruction(int localVariableIndex) {
        this(0x16, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x16;
    }

    @Override
    public String getSubMnemonic() {
        return "lload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return long.class;
    }

}
