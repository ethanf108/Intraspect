package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x03, mnemonic = "iconst_0")
public final class IConst_0Instruction extends IConstInstruction {

    public IConst_0Instruction() {

    }

    @Override
    public Integer getValue() {
        return 0;
    }
}
