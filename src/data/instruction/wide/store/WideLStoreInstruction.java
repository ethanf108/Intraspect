package data.instruction.wide.store;

import data.ClassFile;

public final class WideLStoreInstruction extends WideStoreInstruction {

    WideLStoreInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideLStoreInstruction(int localVariableIndex) {
        this(0x37, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x37;
    }

    @Override
    public String getSubMnemonic() {
        return "lstore_w";
    }

    @Override
    public Class<?> getStoreType() {
        return long.class;
    }

}
