package edu.rit.csh.intraspect.data.instruction.wide.load;

import edu.rit.csh.intraspect.data.ClassFile;

public final class WideDLoadInstruction extends WideLoadInstruction {

    WideDLoadInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    public WideDLoadInstruction(final int localVariableIndex) {
        this(0x18, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x18;
    }

    @Override
    public String getSubMnemonic() {
        return "dload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return double.class;
    }

}
