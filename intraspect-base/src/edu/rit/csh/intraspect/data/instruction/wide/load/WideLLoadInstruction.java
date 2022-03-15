package edu.rit.csh.intraspect.data.instruction.wide.load;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class WideLLoadInstruction extends WideLoadInstruction {

    WideLLoadInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @AssembleInject
    public WideLLoadInstruction(final int localVariableIndex) {
        this(0x16, localVariableIndex);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return super.isValid(ref) && this.subOpcode == 0x16;
    }

    @Override
    public String getSubMnemonic() {
        return "lload_w";
    }

    @Override
    public Class<?> getLoadType() {
        return long.class;
    }

}
