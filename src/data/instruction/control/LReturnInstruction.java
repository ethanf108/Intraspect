package data.instruction.control;

import data.instruction.Opcode;

@Opcode(opcode = 0xAD, mnemonic = "lreturn")
public final class LReturnInstruction extends ReturnInstruction {

    public LReturnInstruction() {
    }

    @Override
    public Class<?> getReturnType() {
        return long.class;
    }
}
