package edu.rit.csh.intraspect.data.instruction.wide.load;

import edu.rit.csh.intraspect.data.ClassFile;

public final class WideFLoadInstruction extends WideLoadInstruction {

    WideFLoadInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideFLoadInstruction(final int localVariableIndex) {
        this(0x17, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x17;
    }

    @Override
    public String getSubMnemonic() {
        return "fload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return float.class;
    }

}
