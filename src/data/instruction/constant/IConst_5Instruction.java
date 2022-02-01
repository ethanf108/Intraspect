package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x08, mnemonic = "iconst_5")
public final class IConst_5Instruction extends IConstInstruction {

    public IConst_5Instruction() {

    }

    @Override
    public Integer getValue() {
        return 5;
    }
}
