package edu.rit.csh.intraspect.data.instruction.wide.store;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class WideAStoreInstruction extends WideStoreInstruction {

    WideAStoreInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @AssembleInject
    public WideAStoreInstruction(final int localVariableIndex) {
        this(0x3A, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
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
