package data.instruction.wide.store;

import data.ClassFile;

public final class WideIStoreInstruction extends WideStoreInstruction {

    WideIStoreInstruction(int subOpcode, int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideIStoreInstruction(int localVariableIndex) {
        this(0x36, localVariableIndex);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x36;
    }

    @Override
    public String getSubMnemonic() {
        return "istore_w";
    }

    @Override
    public Class<?> getStoreType() {
        return int.class;
    }

}
