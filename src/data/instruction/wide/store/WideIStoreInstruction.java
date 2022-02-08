package data.instruction.wide.store;

import data.ClassFile;

public final class WideIStoreInstruction extends WideStoreInstruction {

    WideIStoreInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideIStoreInstruction(final int localVariableIndex) {
        this(0x36, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
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
