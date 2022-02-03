package data.instruction.control;

import data.instruction.Opcode;

@Opcode(opcode = 0xB0, mnemonic = "areturn")
public final class AReturnInstruction extends ReturnInstruction {

    public AReturnInstruction() {
    }

    @Override
    public Class<?> getReturnType() {
        return Object.class;
    }
}
