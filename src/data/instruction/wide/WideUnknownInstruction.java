package data.instruction.wide;

import data.ClassFile;

public final class WideUnknownInstruction extends WideInstruction {

    WideUnknownInstruction(int subOpcode) {
        super(subOpcode, -1);
    }

    @Override
    public boolean isValid(ClassFile ref) {
        return false;
    }

    @Override
    public String getSubMnemonic() {
        return "wide_???";
    }
}
