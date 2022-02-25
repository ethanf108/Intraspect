package edu.rit.csh.intraspect.data.instruction.misc;

import edu.rit.csh.intraspect.data.ClassFile;
import edu.rit.csh.intraspect.data.instruction.Instruction;
import edu.rit.csh.intraspect.edit.assemble.AssembleInject;

public final class UnknownInstruction extends Instruction {

    public static final String UNKNOWN_INSTRUCTION_NAME = "???";

    private final int opcode;

    @AssembleInject
    public UnknownInstruction(final int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getNumOperands() {
        return 0;
    }

    @Override
    public int[] getOperands() {
        return new int[0];
    }

    @Override
    public boolean isValid(final ClassFile ref) {
        return false;
    }

    @Override
    public int getOpcode() {
        return this.opcode;
    }

    @Override
    public String getMnemonic() {
        return UNKNOWN_INSTRUCTION_NAME;
    }
}
