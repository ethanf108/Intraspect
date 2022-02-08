package data.instruction.wide.store;

import data.ClassFile;

public final class WideDStoreInstruction extends WideStoreInstruction {

    WideDStoreInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideDStoreInstruction(final int localVariableIndex) {
        this(0x39, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x39;
    }

    @Override
    public String getSubMnemonic() {
        return "dstore_w";
    }

    @Override
    public Class<?> getStoreType() {
        return double.class;
    }

}
