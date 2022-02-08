package edu.rit.csh.intraspect.data.instruction.wide;

import edu.rit.csh.intraspect.data.ClassFile;

public final class WideUnknownInstruction extends WideInstruction {

    WideUnknownInstruction(final int subOpcode) {
        super(subOpcode, -1);
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return false;
    }

    @Override
    public String getSubMnemonic() {
        return "wide_???";
    }
}
