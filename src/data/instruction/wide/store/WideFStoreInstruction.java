package data.instruction.wide.store;

import data.ClassFile;

public final class WideFStoreInstruction extends WideStoreInstruction {

    WideFStoreInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideFStoreInstruction(final int localVariableIndex) {
        this(0x38, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x38;
    }

    @Override
    public String getSubMnemonic() {
        return "fstore_w";
    }

    @Override
    public Class<?> getStoreType() {
        return float.class;
    }

}
