package data.instruction;

import data.ClassFile;

public final class UnknownInstruction extends Instruction {

    public static final String UNKNOWN_INSTRUCTION_NAME = "???";

    private final int opcode;

    public UnknownInstruction(int opcode) {
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
    public boolean isValid(ClassFile ref) {
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
