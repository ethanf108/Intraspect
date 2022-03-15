package edu.rit.csh.intraspect.data.instruction.wide.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class WideILoadInstruction extends WideLoadInstruction {

    WideILoadInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @AssembleInject
    public WideILoadInstruction(final int localVariableIndex) {
        this(0x15, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x15;
    }

    @Override
    public String getSubMnemonic() {
        return "iload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return int.class;
    }

}
