package data.instruction.control;

import data.instruction.Opcode;

@Opcode(opcode = 0xAE, mnemonic = "freturn")
public final class FReturnInstruction extends ReturnInstruction {

    public FReturnInstruction() {
    }

    @Override
    public Class<?> getReturnType() {
        return float.class;
    }
}
