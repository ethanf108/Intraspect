package edu.rit.csh.intraspect.data.instruction.wide;

import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class WideRetInstruction extends WideInstruction {

    public WideRetInstruction(final int subOpcode, final int localVariableIndex) {
        super(subOpcode, localVariableIndex);
    }

    @AssembleInject
    public WideRetInstruction(final int localVariableIndex) {
        super(0xA9, localVariableIndex);
    }

    @Override
    public String getSubMnemonic() {
        return "ret_w";
    }
}
