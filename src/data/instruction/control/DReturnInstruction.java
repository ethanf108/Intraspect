package data.instruction.control;

import data.instruction.Opcode;

@Opcode(opcode = 0xAF, mnemonic = "dreturn")
public final class DReturnInstruction extends ReturnInstruction {

    public DReturnInstruction() {
    }

    @Override
    public Class<?> getReturnType() {
        return double.class;
    }
}
