package edu.rit.csh.intraspect.data.instruction.wide.store;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class WideFStoreInstruction extends WideStoreInstruction {

    WideFStoreInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @AssembleInject
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
