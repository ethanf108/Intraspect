package data.instruction.wide.load;

import data.ClassFile;

public final class WideDLoadInstruction extends WideLoadInstruction {

    WideDLoadInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideDLoadInstruction(int localVariableIndex) {
        this(0x18, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x18;
    }

    @Override
    public String getSubMnemonic() {
        return "dload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return double.class;
    }

}
