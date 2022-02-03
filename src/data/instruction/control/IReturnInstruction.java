package data.instruction.control;

import data.instruction.Opcode;

@Opcode(opcode = 0xAC, mnemonic = "ireturn")
public final class IReturnInstruction extends ReturnInstruction {

    public IReturnInstruction() {
    }

    @Override
    public Class<?> getReturnType() {
        return int.class;
    }
}
