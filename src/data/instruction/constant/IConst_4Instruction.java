package data.instruction.constant;

import data.instruction.Opcode;

@Opcode(opcode = 0x07, mnemonic = "iconst_4")
public final class IConst_4Instruction extends IConstInstruction {

    public IConst_4Instruction() {

    }

    @Override
    public Integer getValue() {
        return 4;
    }
}
