package data.instruction.wide.store;

import data.ClassFile;

public final class WideAStoreInstruction extends WideStoreInstruction {

    WideAStoreInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideAStoreInstruction(int localVariableIndex) {
        this(0x3A, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x3A;
    }

    @Override
    public String getSubMnemonic() {
        return "astore_w";
    }

    @Override
    public Class<?> getStoreType() {
        return Object.class;
    }

}
