package edu.rit.csh.intraspect.data.instruction.wide.load;

import edu.rit.csh.intraspect.data.ClassFile;

public final class WideALoadInstruction extends WideLoadInstruction {

    WideALoadInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideALoadInstruction(final int localVariableIndex) {
        this(0x19, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x19;
    }

    @Override
    public String getSubMnemonic() {
        return "aload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return Object.class;
    }
}
